package test.functional.exam;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.exam.dao.ExamDao;
import com.thoughtworks.gaia.exam.model.ExamModel;
import com.thoughtworks.gaia.exam.service.ExamService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jkwang on 1/12/17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class ExamServiceFunctionalTest {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamDao examDao;

    @Test(expected = NotFoundException.class)
    public void should_exam_notexists_when_given() {
        examService.getExam(1L);
    }

    @Test
    public void should_exam_exists_when_given() {
        //given
        ExamModel exammodel = new ExamModel();
        exammodel.setLogicQuestionCount(10);
        exammodel.setProgQuestionCount(10);
        exammodel.setStatusTypeId(1L);
        exammodel.setName("test1");
        exammodel.setTimeCreated(DateTime.now().toDate());
        examDao.save(exammodel);

        long examid = exammodel.getId();
        long newexamid = examService.getExam(examid).getId();
        //when
        //then
        Assert.assertEquals(examid, newexamid);
    }
}
