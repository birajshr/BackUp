/* Name: Biraj Shrestha
 * UT EID: BS29898
 */

public class Program3 implements IProgram3
{

    private int numClasses;
    private int maxGrade;
    GradeFunction gf;

    public Program3() {
    	 this.numClasses = 0;
         this.maxGrade = 0;
         this.gf = null;
    }

    public void initialize(int n, int g, GradeFunction gf) {
    	 this.numClasses = n;
         this.maxGrade = g;
         this.gf = gf;
    }
    
    
    /*
     * This method returns an array that is the size of the number of classes.
     * Entry i in the array is the (integer) number of hours one should spend on 
     * class i (0 <= i <= n-1) in maximizing all of one's grades.
     */
    public int[] computeHours(int totalHours)
    {
    	int totalHoursOffset 	= 	totalHours + 1;							//Hours must include 0h spent
    	int OPT[][] 			= 	new int[numClasses][totalHoursOffset];	//Storage for optimal hour
    	int Keep[][] 			= 	new int[numClasses][totalHoursOffset];	//Storage for Kept class and hour
    	int computeHours[] 		= 	new int[numClasses];					//Number of hours to spend for each class

    	int max, Grade, timeChosen;											//Initialize variables for algorithm
    	timeChosen = 0;
    	
    	for(int h = 0; h < totalHoursOffset; h++)							//Set Optimal Time for Class 0
    	{
    		OPT[0][h] = gf.grade(0, h);
    		Keep[0][h] = h;													//Keep value for Class 0
    	}
    	
    	for(int currClass = 1; currClass < numClasses; currClass++)			//Iteration over classes
    	{
    		for(int currHour = 0; currHour < totalHoursOffset; currHour++)	//Iterations over hours
    		{
    			max = 0;													//Initialize Grade=0
    			Grade = 0;
    			for(int hour = 0; hour <= currHour; hour++)					//Iterations over potential hour
    			{
    				Grade = OPT[currClass - 1][currHour - hour] + gf.grade(currClass, hour);	//Grade for current hour and class
    				if(Grade > max)											//Check Grade better than previous
    				{
    					max = Grade;										//Set max to Grade if greater
    					timeChosen = hour;									//Set time chosen to the hour
    				}
    			}
    			Keep[currClass][currHour] = timeChosen;						//Save time chosen
    			OPT[currClass][currHour] = max;								//Save optimal grade	
    		}
    	}
    	
    	int currKeep;														//Initialize variables for mapping
    	int totalHoursCounter = totalHours;
    	
    	for(int traverseClass = (numClasses - 1); traverseClass >= 0; traverseClass--)	//Class Iteration (Reverse)
    	{
    		currKeep = Keep[traverseClass][totalHoursCounter];				//Gets hour kept
    		computeHours[traverseClass] = currKeep;							//Maps hour to class
    		totalHoursCounter = totalHoursCounter - currKeep;				//Reduces total hours 
    	}
    	
    	return computeHours;												//returns statement
    }

    public int[] computeGrades(int totalHours)
    {
    	int totalHoursOffset 	= 	totalHours + 1;							//Hours must include 0h spent
    	int OPT[][] 			= 	new int[numClasses][totalHoursOffset];	//Storage for optimal hour
    	int Keep[][] 			= 	new int[numClasses][totalHoursOffset];	//Storage for Kept class and hour
    	int computeHours[] 		= 	new int[numClasses];					//Number of hours to spend for each class
    	int computeGrades[] 	= 	new int[numClasses];					//Grade for each class
    	
    	int max, Grade, timeChosen;											//Initialize variables for algorithm
    	timeChosen = 0;
    	
    	for(int h = 0; h < totalHoursOffset; h++)							//Set Optimal Time for Class 0
    	{
    		OPT[0][h] = gf.grade(0, h);
    		Keep[0][h] = h;													//Keep value for Class 0
    	}
    	
    	for(int currClass = 1; currClass < numClasses; currClass++)			//Iteration over classes
    	{
    		for(int currHour = 0; currHour < totalHoursOffset; currHour++)	//Iterations over hours
    		{
    			max = 0;													//Initialize Grade=0
    			Grade = 0;
    			for(int hour = 0; hour <= currHour; hour++)					//Iterations over potential hour
    			{
    				Grade = OPT[currClass - 1][currHour - hour] + gf.grade(currClass, hour);	//Grade for current hour and class
    				if(Grade > max)											//Check Grade better than previous
    				{
    					max = Grade;										//Set max to Grade if greater
    					timeChosen = hour;									//Set time chosen to the hour
    				}
    			}
    			Keep[currClass][currHour] = timeChosen;						//Save time chosen
    			OPT[currClass][currHour] = max;								//Save optimal grade	
    		}
    	}
    	
    	int currKeep;														//Initialize variables for mapping
    	int totalHoursCounter = totalHours;
    	
    	for(int traverseClass = (numClasses - 1); traverseClass >= 0; traverseClass--)	//Class Iteration (Reverse)
    	{
    		currKeep = Keep[traverseClass][totalHoursCounter];				//Gets hour kept
    		computeHours[traverseClass] = currKeep;							//Maps hour to class
    		totalHoursCounter = totalHoursCounter - currKeep;				//Reduces total hours 
    	}
    	
    	for(int currClass = 0; currClass<numClasses; currClass++)			//Iteration throught classes
    	{
    		computeGrades[currClass] = gf.grade(currClass, computeHours[currClass]);	//Set ComputeGrades to grade for class
    	}
    	
    	
        return computeGrades;
    }

}