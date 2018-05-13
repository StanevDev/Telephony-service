package edu.jam.telephony;

import edu.jam.telephony.dao.impl.ServiceDao;
import edu.jam.telephony.model.entity.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDaoTest extends Assert {

    @Autowired
    ServiceDao dao;

    @Test
    public void getBySubId() {
        int sub_id = 9;

        List<Service> services = dao.getBySubscriber(sub_id);

        assertNotNull(services);
    }

    @Test
    public void getAll() {
        List<Service> services = dao.getAll();
        assertNotNull(services);
    }

}
