
package com.sankuai.inf.leaf.segment;

import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.segment.model.SegmentBuffer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
public class SegmentIDGenImplTest {
    @InjectMocks
    private SegmentIDGenImpl segmentIDGen;

    @Mock
    private Map<String, SegmentBuffer> cache;

    @Mock
    private IDAllocDao dao;

    @Test
    public void testGet(){
        ArrayList<String> tags = new ArrayList<>();
        tags.add("test");
        tags.add("order");
        Mockito.when(dao.getAllTags()).thenReturn(tags);
        segmentIDGen.init();
        segmentIDGen.get("test");
    }

    @Test
    public void testGetByTagIsNull(){
        segmentIDGen.init();
        segmentIDGen.get("test");
    }

    @Test
    public void testGetByCacheTag(){
        ArrayList<String> tags = new ArrayList<>();
        tags.add("test");
        tags.add("order");
        Mockito.when(dao.getAllTags()).thenReturn(tags);
        Set<String> sets = new HashSet<>();
        sets.add("test");
        Mockito.when(cache.keySet()).thenReturn(sets);
        segmentIDGen.init();
    }

    @Test
    public void testGetByCacheTagByUpdateTimestamp(){
        ArrayList<String> tags = new ArrayList<>();
        tags.add("test");
        tags.add("order");
        Mockito.when(dao.getAllTags()).thenReturn(tags);
        Set<String> sets = new HashSet<>();
        sets.add("test");
        Mockito.when(cache.keySet()).thenReturn(sets);
        segmentIDGen.init();
        SegmentBuffer segmentBuffer = new SegmentBuffer();
        segmentBuffer.setUpdateTimestamp(0L);
        Mockito.when(cache.get("test")).thenReturn(segmentBuffer);
        segmentIDGen.get("test");
    }
}

