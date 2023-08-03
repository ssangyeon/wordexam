package toy.example.wordexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.example.wordexam.domain.Challenger;
import toy.example.wordexam.domain.Group;
import toy.example.wordexam.dto.ChallengerForm;
import toy.example.wordexam.repository.ChallengerRepository;
import toy.example.wordexam.repository.GroupRepository;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기에는 가급적 readOnly에 true 넣어주자 이 클래스는 읽기가 많으므로 readonly true 넣고 join에만 따로표시
@RequiredArgsConstructor //파이널 있는 필드만 가지고생성자를 만들어줌(베스트)
public class ChallengerService {

//    @Autowired //스프링이 스프링빈에 있던 리포지토리를 주입해준다(필드주입)
//    private ChallengerRepository challengerRepository;
    //setter injection 은 필드주입과 달리 테스트할때 setter로 바꾸기 가능 단 애플리케이션 조립시점에 세팅이끝나므로 조립한이후 바꿀일 없음
    //성성자 injection 중간에 set으로 바꿀수 없고, 테스트할때 직접주입해야하므로 좋음
    private final ChallengerRepository challengerRepository; //final 넣어 변경 안하는거 인지가능
    private final GroupRepository groupRepository;
////    @Autowired: 생성자 하나여서 생략가능
//    public ChallengerService(ChallengerRepository challengerRepository){
//        this.challengerRepository = challengerRepository;
//    }

   public Group makeGroup(String groupName){
       Group group = new Group();
       group.setGroupName(groupName);
       groupRepository.save(group);
       return group;
   }

    /**
     * 회원 가입
     */
    @Transactional //읽기가 아닌 쓰기에 readOnly true를 넣으면 데이터 변경이 안되므로 default값인 false
    public Long join(String challengerName, String groupName, String challengerPassword){
        Group validGroup = isValidGroup(groupName);
        validateDuplicateChallenger(challengerName);
        Challenger challenger = Challenger.createChallenger(challengerName,validGroup,challengerPassword);
        challengerRepository.save(challenger); //save하면 db가 아닌 영속성컨테스트 안에 있어도 id값이 부여되어 id값을 리턴가능
        return challenger.getId();
    }
    /**
     * 로그인
     */
    public ChallengerForm login(ChallengerForm form) {
        /*
        1.회원이 입력한 그룹이름으로 db에서 조회를함
        2.db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
         */
//        Optional<Challenger> byGroupName = challengerRepository.findByGroupName(form.getGroupName());
//        if(byGroupName.isPresent()){
//            //조회 결과가 있다(해당 이메일을 가진 회원정보가 있다.)
//            Challenger challenger = byGroupName.get();
//            if(challenger.getPassword().equals(form.getChallengerPassword())){
//                //비밀번호 일치
//                //entity -> dto변환 후 리턴
//                ChallengerForm challengerForm = ChallengerForm.toChallengerForm(challenger);
//                return challengerForm;
//            }else{
//                //비밀번호 불일치
//                return null;
//            }
//        }else{
//            //조회 결과가 없다(해당 이메일을 가진 회원정보가 없다.)
//            return null;
//        }
        List<Challenger> byChallengerName = challengerRepository.findByName(form.getChallengerName());
        if (byChallengerName.isEmpty()) {
            return null;
        } else {
            Challenger challenger = byChallengerName.get(0);
            if (challenger.getPassword().equals(form.getChallengerPassword())) {
                ChallengerForm challengerForm = ChallengerForm.toChallengerForm(challenger);
                return challengerForm;
            }
            else {
                return null;
            }
        }

    }


    private Group isValidGroup(String groupName) {
        List<Group> groupList = groupRepository.findByName(groupName);
        if(groupList.isEmpty()){
            return makeGroup(groupName);
        }
        return groupList.get(0);
    }

    private void validateDuplicateChallenger(String challenger) {
        //EXCEPTION
        List<Challenger> findChallengers = challengerRepository.findByName(challenger);//동시에 호출될경우 대비하여 name유니크 제약조건 걸어두는것도 좋은방법
        if(!findChallengers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //도전자 전체 조회
    public List<Challenger> findChallengers(){
        return challengerRepository.findAll();
    }

    public Challenger findOne(Long challengerId){
        return challengerRepository.findOne(challengerId);
    }


}
