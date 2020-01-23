//-----------------------------------------//
// MATH226 - Numerical Methods for EE 
// Project 04
// 
// Name-Surname: <Göktuð YILDIRIM>
// Student ID: <041502012>
//-----------------------------------------// 
package odev;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	// special functions
	public static float[][] multiplyMatrix(float b[][], float a[][], int l, int n) {
		
		// Product of Matrices B (l x n) and A (n x n) is defined as a Matrix C (l x n) 
		float[][] c = new float[l][n];
		
		for (int i=0; i<l; ++i) {
		    for (int j=0; j<n; ++j) {
		      for (int k=0; k<n; ++k) {
		        c[i][j] += b[i][k] * a[k][j]; 
		      }   		
		    }        		
		}	
		return c;
	}
	public static float[][] inverseP(float a[][], int n){
		float[][] b = new float[n][n];
		b=a;
		for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                float temp = b[i][j];
                b[i][j] = b[j][i];
                b[j][i] = temp;
            }
        }
		return b;
	}
	public static void rowInterchange(float matrix[][],int firstRow, int secondRow,int row, int column) {
		
		float[][] temp1 = new float[1][column];
		float[][] temp2 = new float[1][column];

		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(i==firstRow)
				temp1[0][j] = matrix[i][j];
			}
		}
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(i==secondRow)
				temp2[0][j] = matrix[i][j];
			}
		}
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(i==secondRow)
				matrix[i][j] = temp1[0][j];
			}
		}
	
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(i==firstRow)
				matrix[i][j] = temp2[0][j];
			}
		}
		
	}

	public static void printMatrix(float a[][],int i, int j) {
		
		for(int q=0; q<i; q++) {
			for(int e=0; e<j; e++) {
				System.out.print(a[q][e]+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	// starting main function
	public static void main(String[] args) throws FileNotFoundException {
		
		java.io.File file = new java.io.File("A.txt"); // Creating "file" object
		@SuppressWarnings("resource")
		Scanner input = new Scanner(file); // Creating "input" object
		String line;
		int n=0; // Size of A matrix
		ArrayList<Float> readElements = new ArrayList<>();
		
		while(input.hasNextLine()) { // reading A matrix
			line= input.nextLine(); // Matrix rows are storing in "line" variable.
			String parts[] = line.split(" "); // 
			
			for(int i=0; i<parts.length; i++) {
				float x= Float.valueOf(parts[i]).floatValue();
				readElements.add(x); // All of elements is stored in list. Also, There are n x n elements will store in the list.
			}
			n++; // Size of matrix increases in each row	
		}
		
		float [][] b = new float[n][1];
		float [][] pB = new float[n][1]; // this matrix will be Pxb matrix according to PxL in LU factorization with pivoting.
		float [][] y = new float[n][1]; // L(y) = b
		float [][] x = new float[n][1]; // U(x) = y
		
		java.io.File file1 = new java.io.File("B.txt");
		@SuppressWarnings("resource")
		Scanner input1 = new Scanner(file1);
		int h = 0; // counter will increase for each row.
		while(input1.hasNextLine()) { //reading b matrix
			line = input1.nextLine();
			b[h][0] = Float.valueOf(line).floatValue();
			pB[h][0] =  Float.valueOf(line).floatValue();
			h++;
		}
				
		float [][] a = new float [n][n]; 
		float [][] a1 = new float [n][n]; // Due to change "a" matrix in first part of project, I created the same "a1" matrix that I'll use this matrix in the second part of project.
		
		int g=0; // Index number of elements in array list.
		for(int i=0; i<n; i++) { // This for loop obtains A matrix from list.
			for(int j=0; j<n; j++) {
				a[i][j]=readElements.get(g);
				a1[i][j]=readElements.get(g);
				g++;
			}
		}
		
		float [][] l = new float [n][n]; // This matrix will be L matrix in first part of project.
		float [][] u = new float [n][n]; // This matrix will be U matrix in first part of project.
				
		System.out.println("//////////////////////////PART 1//////////////////////////\n");
		System.out.println("A Matrix\n-------------");
		
		for(int i=0; i<n; i++) {
			for(int k=0; k<n; k++) {
				System.out.print(a[i][k]+" ");
			}
			System.out.println("\n");
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=i-1; j++) {		// Executing lower triangular matrix		
				float temporary = a[i-1][j-1];
				for(int k=1; k<=j-1; k++) {
					temporary = temporary - a[i-1][k-1]*a[k-1][j-1];					
				}
				a[i-1][j-1] = temporary / a[j-1][j-1];
				l[i-1][j-1] = temporary / a[j-1][j-1]; //adding element to real lower
			}
			
			for(int j=i; j<=n; j++) {		//Executing upper triangular matrix	
				float temporary = a[i-1][j-1];
				for(int k=1; k<=i-1; k++) {
					temporary = temporary - a[i-1][k-1]*a[k-1][j-1];					
				}
			
				a[i-1][j-1] = temporary;
				u[i-1][j-1] = temporary; //adding element to real upper
			}
		}
		
		for(int i=0; i<n; i++) { // adding zeros and ones for real lower and upper matrix
			for(int j=0; j<n; j++) {
				if(i==j) 
					l[i][j]=1;
				if(j>i)
					l[i][j]=0;
				if(i>j)
					u[i][j]=0;
			}
		}
	
		System.out.println("Temporary Matrix\n-------------");
		
		for(int i=0; i<n; i++) {
			for(int k=0; k<n; k++) {
				System.out.print(a[i][k]+" ");
			}
			System.out.println("\n");
		}
	
		System.out.println("Lower Triangular Matrix\n-------------");
		
		for(int i=0; i<n; i++) {
			for(int k=0; k<n; k++) {
				System.out.print(l[i][k]+" ");
			}
			System.out.println("\n");
		}
		
		System.out.println("Upper Triangular Matrix\n-------------");
		
		int singular=0; // control value for singularity
		for(int i=0; i<n; i++) { // Singularity finds out in this for loop.
			for(int k=0; k<n; k++) {
				if(i==k && a[i][k]==0)
					singular++; // if there is zero in diagonal of U matrix, "singular" variable increases.
				
				System.out.print(u[i][k]+" ");
			}
			System.out.println("\n");
		}
		
		//PART 2 OF THE PROJECT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("//////////////////////////PART 2//////////////////////////");
		
		float max=0, pivot=0;
		int rowOfMax=0; // This variable will be equal to 
		float newValueForElementer;
		//initial matrices are equal to identity matrix.
		float[][][] elementaryMatrices = new float[n-1][n][n];          // Also all of elementary matrices will be stored in this.
		float[][][] inverseElementaryMatrices = new float[n-1][n][n];   // Also all of inverse of elementary matrices will b stored in this.
		float[][][] permutationMatrices = new float[n-1][n][n]; 	    // Also all of permutation matrices will be stored in this.
		float[][][] inversePermutationMatrices = new float[n-1][n][n];  // Also all of permutation matrices will be stored in this.
		
		// filling matrices for being identity matrix
		for(int k=0; k<n-1; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(i==j) {
						elementaryMatrices[k][i][j]=1;
						inverseElementaryMatrices[k][i][j]=1;
						permutationMatrices[k][i][j]=1;
					}
					else {
						elementaryMatrices[k][i][j]=0;
						inverseElementaryMatrices[k][i][j]=0;
						permutationMatrices[k][i][j]=0;
					}
				}
			}
		}
		
		// all of matrix operations are starting after this point
		
		for(int j=0; j<n-1; j++) {
			
			// algorithm order in this first loop that has i variable:
			// 1) sorting elements in the same column, creating permutation matrix from identity matrix with rowInterchange() function.
		    //    rowInterchange(matrix, id of first row, id of second row, total number of rows of matrix, total number of columns of matrix).
			// 2) multiplying a1 matrix with permutation matrix using with multiplyMatrix() function.
			//    multiplyMatrix(left matrix right matrix, number of rows of left matrix, number of columns of right matrix) this function returns two-dimensional matrix.
			// 3) creating inverse of permutation matrix using with inverseP() function.
			//    inverseP(permutation matrix,n) Transposing with permutation matrix.
			
			for(int i=j; i<n; i++) {
				if(i==j) { // I assumed that i=j index (always top elements of column according to loop) is maximum value of j column.
					max = a1[i][j];
					rowOfMax = i; // Address of maximum value
				}
				else { // if top element is not maximum value.
					if(Math.abs(a1[i][j])>Math.abs(max)) {
					max=  a1[i][j];
					rowOfMax = i;
					}
				}
			}
			
			rowInterchange(permutationMatrices[j],rowOfMax,j,n,n); // row interchanging operation for identity matrix to obtain permutation matrix
			a1 = multiplyMatrix(permutationMatrices[j], a1, n, n); // multiplying A matrix and permutation matrix
			inversePermutationMatrices[j] = inverseP(permutationMatrices[j],n); 
			
			// algorithm order in this second loop that has i variable:
			// 1) creating value that will replace with zero in identity matrix and identity matrix will be equal to elementary matrix.
			// 2) creating elementary matrix from identity matrix.
			// 3) multiplying elementary matrix with A matrix.
			for(int i=j; i<n; i++) {
				if(i==j) { // pivot is always at i=j index of A matrix.
					pivot = a1[i][j]; 
				}
				else {
					if(a1[i][j]!=0) {
						newValueForElementer = -a1[i][j]/pivot; // this value will replace with zero in identity matrix.
						elementaryMatrices[j][i][j]=newValueForElementer; // creating elementary matrix
						inverseElementaryMatrices[j][i][j]=-newValueForElementer; // creating inverse of elementary matrix
					}
				}
			}	
			
			a1 = multiplyMatrix(elementaryMatrices[j], a1, n, n); // this function returns a matrix
			
		}
		
		// At this point a1 matrix is equal to upper triangular matrix
		
		float[][] l1 = new float[n][n]; // This matrix will be equal to L matrix.
				
		// Firstly l1 is identity matrix to use in creating L algorithm. 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j)
					l1[i][j]=1;
				else {                  
					l1[i][j]=0;
				}	
			}
		}
		
		// This for loop executes L matrix
		for(int i=n-2; i>=0; i--) { // Firstly l1 is identity matrix. Algorithm example: L = ..... x inverse(P2) x inverse(M2) x identity (initial l1)
			l1 = multiplyMatrix(inverseElementaryMatrices[i], l1, n, n); // the way to connect a system's output with its input is to use the ineffective element
			l1 = multiplyMatrix(inversePermutationMatrices[i], l1, n, n);
		}
		
		// Print permutation matrices and elementary matrices.
		for(int i=0; i<n-1; i++) {
			System.out.println((i+1)+". Permutation Matrix\n-------------");
			printMatrix(permutationMatrices[i],n,n);
			System.out.println((i+1)+". Elementary Matrix\n-------------");
			printMatrix(elementaryMatrices[i],n,n);
		}
		// Print U matrix
		System.out.println("U MATRIX\n-------------");
		for(int q=0; q<n; q++) {
			for(int e=0; e<n; e++) {
				System.out.print(a1[q][e]+" ");
			}
			System.out.print("\n");
		}
		// Print L matrix
		System.out.println("\nL MATRIX\n-------------");
		for(int q=0; q<n; q++) {
			for(int e=0; e<n; e++) {
				System.out.print(l1[q][e]+" ");
			}
			System.out.print("\n");
		}
		
		// in this algorithm, L matrix will change if L matrix is not lower triangular matrix.
		// but being lower triangular matrix from L is very hard algorithm to explain by writing. I can explain face to face.
		int numberOfNotZeros = 0; // number of not zeros in each rows will store in this variable.
		int counter1 = 0; // if this variable changes, L matrix has been changed to be lower triangular matrix.
		
		for(int i=0; i<n; i++) {
			numberOfNotZeros=0;
			for(int j=0; j<n; j++) {
				if(l1[i][j]!=0) {
					numberOfNotZeros++; // this variable increases, if element of row is not zero.
				}
			}
			
			if(numberOfNotZeros!=i+1) {
				rowInterchange(l1,i,numberOfNotZeros-1,n,n); // Note that I did not stored P matrix in equation (PA=LU).
				rowInterchange(pB,i,numberOfNotZeros-1,n,1); // I manipulated b matrix without P matrix. I just used row interchange function that I wrote.
				counter1++;						
			}
		}
		
		if(counter1!=0) {
			System.out.println("\nL matrix is not lower triangular matrix. Lower triangular L matrix is:\n");
			printMatrix(l1,n,n);
			System.out.println("Now PAQ = LU and b matrix was multiplied with P matrix.\nLUx = Pb\n"); // Actually at this point, I did not use permutation matrix. I just applied row interchange operation on b matrix.
		}
		System.out.println("Pb:\nNote that: I found Pxb matrix out but at the third part of project I applied solution without pivoting.\nIf L matrix is lower triangular matrix, Pb=b");
		printMatrix(pB,n,1);
		//PART 3 OF THE PROJECT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("//////////////////////////PART 3//////////////////////////");
		
		if(singular!=0) { //singularity check
			System.out.println("A matrix is singular and there is no solution\n");	
		}
		
		else { // LU solution without pivoting.
			
			System.out.println("A matrix is nonsingular and applying LU factorization solution without pivoting. ");
			
			for(int j=1; j<=n; j++) { // forward substitution Ly=b
				y[j-1][0] = b[j-1][0] / l[j-1][j-1];
				for(int i=j+1; i<=n; i++) {
					b[i-1][0] = b[i-1][0] - l[i-1][j-1]*y[j-1][0];
				}
			}
			
			for(int j=n; j>=1; j--) { // back substitution Ux=y
				x[j-1][0] = y[j-1][0] / u[j-1][j-1];
				for(int i=1; i<=j-1; i++) {
					y[i-1][0] = y[i-1][0] - u[i-1][j-1]*x[j-1][0];
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			System.out.println("x"+(i+1)+": "+x[i][0]);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		} // function main
	} // class main


