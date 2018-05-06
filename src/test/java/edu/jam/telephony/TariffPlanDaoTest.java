package edu.jam.telephony;

import edu.jam.telephony.dao.impl.TariffPlanDao;
import edu.jam.telephony.model.entity.TariffPlan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TariffPlanDaoTest extends Assert {

    @Autowired
    TariffPlanDao dao;

    @Test
    public void getCount() {
        assertTrue(dao.getCount() > 0);
    }

    @Test
    public void tariffsValidate() {
        var tariffs = dao.getAll();
        int count = dao.getCount();

        assertNotNull(tariffs);
        assertEquals(tariffs.size(), count);

        for (var plan : tariffs) {
            assertNotNull(plan);
            assertNotNull(plan.getDescription());
            assertNotNull(plan.getExpiresDate());
            assertNotNull(plan.getName());
            assertNotNull(plan.getPrice());
            assertNotNull(plan.getRegion());
        }

    }

    @Test
    public void batchUpdate() {
        int startCount = dao.getCount();

        var testTariffs = new ArrayList<TariffPlan>() {{
            add(new TariffPlan(
                    0,
                    BigDecimal.ZERO,
                    new Date(System.currentTimeMillis()),
                    "Kiev",
                    "testTariff",
                    "Without description"));
        }};

        dao.addAll(testTariffs);

        assertNotEquals(startCount, dao.getCount());
    }
}
