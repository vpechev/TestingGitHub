 //Console reader
 static int[][] reader(){
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();

  int[][]result = new int[n][n];
  
  for (int i = 0; i < n; i++) {
   for (int j = 0; j < n; j++) {
    result[i][j]=in.nextInt();  
   }   
  }
  
  return result;   
 }
 
 //File Reader
  static int[][] readFromFile(String path) throws FileNotFoundException{
   int size;
   int[][] result = new int[0][0];
   int i=0;
   
   try {
    BufferedReader reader = new BufferedReader(new FileReader(path));
    String line = null;
    String[] parts;
    while ((line = reader.readLine()) != null) {
     if(i==0){
      line = line.trim();
      size = Integer.parseInt(line);
      //System.out.println(line);
      result = new int[size][size];
     }
     else{ 
      //System.out.println(line);
      parts = line.split("\\s+");
      for (int k = 0; k < parts.length; k++) {
       result[i-1][k] = Integer.parseInt(parts[k]);
      }  
     }
     
     i++;
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
    
   return result;
  }
 
 //Paths Counter
 static int srcFreePaths(int[][] arr){
  int freePathCounter=0;
  boolean checkH;
  boolean checkV;
  
  for (int i = 0; i < arr.length; i++) {
   
   checkH = true;
   checkV = true;
   
   for (int j = 0; j < arr.length; j++) {
    if(arr[i][j]!=0)
     checkH=false;
    if(arr[j][i]!=0)
     checkV=false; 
   } 
   
   if(checkV)
    freePathCounter++;
   
   if(checkH)
    freePathCounter++;  
  }

  return freePathCounter;
 }
 
 //Type Counter and Correct input insurance
 static int srcTypeCount(int[][] arr){
  
  int typePointer=0;
  int FigureHeight;
  int FigureWidth;
  int curHeight = 0;
  int curWidth;
  
  for (int i = 0; i < arr.length; i++) {
   for (int j = 0; j < arr.length; j++) {
    
    if(arr[i][j]==1){ 
     FigureHeight = 0;
     FigureWidth = 0;
     
     for (int i2 = i; i2 < arr.length; i2++) {
      curWidth = 0;
      for (int j2 = j; j2 < arr.length; j2++) {
       curHeight = 0;
       for (int i3 = i2; i3 < arr.length; i3++) {
        if(j2 == j && i2 == i){
         FigureHeight++;
         //System.out.println("figure");
        }
         
        if(i2 == i){
         curHeight++;
         //System.out.println("current");
        }
                  
        if(i3 + 1 == arr.length){
         break;
        }
        
        if(arr[i3+1][j]==0){
         break;
        }    
       }
       
       if(i2 == i){
        FigureWidth++;
       }
       
       curWidth++;
       
       if(FigureHeight!=curHeight && i2 == i){
        //System.out.println( FigureHeight + " " + curHeight);
        return 0;
       }
       if(i2==i)
       curHeight = 0;
       //System.out.println("zero current");
       
       if(j2 + 1 == arr.length){
        arr[i2][j2] = 0;
        break;
       }
       if(arr[i2][j2+1]==0){
        arr[i2][j2] = 0;
        break;
       }
       else
        arr[i2][j2] = 0;
       
      }
      
      
      if(FigureWidth!=curWidth){
       //System.out.println("Incorrect data input");
       return 0;
      }
      
      if(i2 + 1 == arr.length){
       arr[i2][j]=0;
       break;
      }
      else if(arr[i2+1][j]==0){
       arr[i2][j]=0;
       break;
      }
      else 
       arr[i2][j]=0;
     }
     
     typePointer++;       
    }         
   }
  }
  
  return typePointer;
 }
 
 public static void main(String[] args) {
  /*CONSOLE
  Scanner in = new Scanner(System.in);
  
    break;  
  }
  
  int arr[][]= reader(n);
   */
  
  //File 
  long start = System.currentTimeMillis();
  int arr[][];
  for (int i = 0; i < 50; i++) {
   try {
    arr = readFromFile("/home/daniel/Downloads/fields/output" + i + ".txt");
    
    int pathCount = srcFreePaths(arr);
    int typeCount = srcTypeCount(arr); 
    
    if(typeCount == 0)
     System.out.println("wrong input data");
    
    else{
     System.out.println("total Path count: " + pathCount);
     System.out.println("total Type count: " + typeCount);
    }

   } catch (FileNotFoundException e) {
    e.printStackTrace();
   }
  }
  System.out.println("Task Time in miliSecs: " + (System.currentTimeMillis() - start));
  
 }
}