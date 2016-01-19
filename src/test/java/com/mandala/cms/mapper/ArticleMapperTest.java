package com.mandala.cms.mapper;

import com.mandala.cms.dao.IArticleDao;
import com.mandala.cms.entity.ArticleEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lin on 16-1-18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class ArticleMapperTest {

    @Autowired
    private IArticleDao articleDao;

    @Test
    public void getCountByWebsiteId() {
        int count = articleDao.getCountByWebsiteId(1);
        Assert.assertNotEquals(0, count);
    }

    @Test
    public void queryPageListByWebsiteIdTest() {
        List<ArticleEntity> list = articleDao.queryPageListByWebsiteId(1, 1, 10, "ARTICLE_BASICID", true);
        Assert.assertNotNull(list);
        Assert.assertNotEquals(0, list.size());
    }
}
