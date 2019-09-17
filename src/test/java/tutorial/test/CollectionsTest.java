package tutorial.test;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.util.ReflectionUtil;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import org.springframework.util.comparator.Comparators;

import static org.hamcrest.Matchers.*;

public class CollectionsTest {

	
	List<Integer> list;
	
	@Before
	public void setUp() throws Exception {
		
		int[] data = {5, 9, 2, 3, 8};
		list = Arrays.stream(data).boxed().collect(Collectors.toList());
		
	}

	@Test
	public void Collections_disjoint() {
		
		List<Integer> list2 = Arrays.stream(new int[] {10,7}).boxed().collect(Collectors.toList());
		assertTrue(Collections.disjoint(list, list2));
	}
	
	@Test
	public void Collections_max_min() throws Exception {
		assert Collections.max(list) == 9;
		assert Collections.min(list) == 2;
	}

	@Test
	public void Collections_sort() throws Exception {
		Collections.sort(list);
		assertThat(list.toArray(), arrayContaining(2,3,5,8,9));
	}
	
	@Test
	public void Collections_sort_comp() throws Exception {

		
		Comparator<Integer> cmp2 = Comparator.comparingInt((a) -> (a%2 == 0 ? -1 : 1));
		Collections.sort(list, cmp2);
		assertThat(list.toArray(), arrayContaining(8,2,9,5,3));
		
		
		Comparator<Integer> cmp = Collections.reverseOrder();
		Collections.sort(list, cmp);
		assertThat(list.toArray(), arrayContaining(9,8,5,3,2));
		

		Collections.reverse(list);
		assertThat(list.toArray(), arrayContaining(2,3,5,8,9));
	}
	
	
}
