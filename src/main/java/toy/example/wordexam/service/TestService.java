package toy.example.wordexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.example.wordexam.domain.*;
import toy.example.wordexam.repository.DistributeRepository;
import toy.example.wordexam.repository.GroupRepository;
import toy.example.wordexam.repository.TestRepository;
import toy.example.wordexam.repository.WordBookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final WordBookRepository wordBookRepository;
    private final GroupRepository groupRepository;
    private final DistributeRepository distributeRepository;

    @Transactional
    public Long test(Long wordBookId,String testName, Long start_range,Long end_range, Long cutLine, Long testTime){
        WordBook wordbook = wordBookRepository.findOne(wordBookId);
        List<Word> words = new ArrayList<>();
        for (Long i = start_range; i <= end_range; i++) {
            words.add(wordbook.getMap().get(i));
        }
        Test test = Test.createTest(testName,cutLine,testTime,words); //form 사용할예정
        testRepository.save(test);
        return test.getId();

    }
    @Transactional
    public void distribute(Long testId, List<Challenger> challenegerList){
        Test test = testRepository.findOne(testId);
        for (Challenger challenger : challenegerList) {
            Distribute distribute = Distribute.createDistribute(test,challenger);
            distributeRepository.save(distribute);
            System.out.println(challenger.getName());
        }
    }

    public List<Distribute> findDistributeList(){
        return distributeRepository.findAll();
    }

    public Group findGroup(String groupName){
        return groupRepository.findByName(groupName).get(0);
    }

    public Long findTestId(String testName){
        Test test = testRepository.findByName(testName).get(0);
        return test.getId();
    }

}
