/* Name: Biraj Shrestha
 * UT EID: BS29898
 */

public class MyGradeFunction implements GradeFunction {

	@Override
	public int grade(int classID, int hours) {
		// TODO Auto-generated method stub
		return (int) Math.floor(fib(hours)/classID);	//returns floor of fib divided by classID
	}
	
	/* Input: int n
	 * Output: interger value of fib sequence
	 */
	public static int fib(int n) {
        if (n < 2) {
           return n;
        }
        else {
        	return fib(n-1)+fib(n-2);
        }
	}

}
