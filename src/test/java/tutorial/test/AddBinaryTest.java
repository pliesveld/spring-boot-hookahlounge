package tutorial.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddBinaryTest {

	    public String addBinary(String a, String b) {
	        boolean carryover = false;
	        Queue
	        // assert a.length() != 0
	        
	        int len = Math.min(a.length(),b.length());
	        
	        char[] charr_a = a.toCharArray();
	        char[] charr_b = b.toCharArray();
	        char[] res = new char[Math.max(a.length(),b.length())];
	        int dest = 0;
	        
	        int idx_a = a.length() - 1;
	        int idx_b = b.length() - 1;
	        
	        for(;idx_a >= 0 || idx_b >= 0;) {
	            
	            char chA = '0';
	            char chB = '0';
	            
	            if(idx_a >= 0)
	                chA = charr_a[idx_a];
	            
	            if(idx_b >= 0)
	                chB = charr_b[idx_b];
	            
	            char n = '\u0000';
	            
	            // chA == 1, chB == 1  // carryover = 1, n = 0
	            // chA == 0, chB == 1  // n = 1
	            // chA == 1, chB == 0 // n = 1
	            // chA == 0, chB == 0 // n = 0
	            
	            // carry over
	            // chA == 1, chB == 1 // n = 1, carryover = 1
	            // chA == 0, chB == 1 // n = 0, carryover = 1
	            // chA == 1, chB == 0 // n = 0, carryover = 1
	            // chA == 0, chB == 0  // n = 1, carryover = 0
	            
	            
	            if(carryover) {
	                if(chA == '0' && chB == '0') {
	                    carryover = false;
	                    n = '1';
	                } 
	            }
	            
	            if(n != '1') {
	                if(chA == '1' && chB == '1') {
	                    carryover = true;
	                    n = '0';
	                } else if(
	                    (chA == '1' && chB == '0') ||
	                    (chA == '0' && chB == '1')
	                ) {
	                	if(carryover)
	                		n = '0';
	                	else
	                		n = '1';
	                	
	                } else {
	                	if(carryover)
	                		n = '1';
                		else
                			n = '0';
	                }
	                
	            }
	            
	         
	            
	            res[dest++] = n;
	            idx_a--;
	            idx_b--;
	        }
	        

	        
	        
	        

        	StringBuilder sb = new StringBuilder();

        	sb.append(new String(res));
        	sb.reverse();

	        if(carryover) {
	        	sb.insert(0, '1');
	        }
            return sb.toString();
            

	    }

    @Test
	public void testCase1() throws Exception {
    	
    	assertTrue("00".equals(new String(new char[] {'0', '0'})));
    	assertTrue("100".equals("1" + new String(new char[] {'0', '0'})));
    	
    	
    	String res = new AddBinaryTest().addBinary("11", "1");
    	assertTrue("100".equals(res));
		
    	
    	
	}
    
    @Test
   	public void testCase2() throws Exception {
       	
       	
       	
       	String res = new AddBinaryTest().addBinary("1010", "1011");
       	assertTrue("10101".equals(res));
   		
       	
       	
   	}
}
