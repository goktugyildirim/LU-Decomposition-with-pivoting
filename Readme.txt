First part of project:
I used algorithm that was shared us in slide.

Second part of project:
I used three for loops passing rows and columns.


At the beginning of the second part of project, I realised that I must pass every columns two times. Because firstly I had to pass for sorting and appliying permutation matrix for each columns.
Secondly I had to pass for elemantary matrix for each column.

Note that for loops only pass lower triangular of matrix..

for(j...) passing columns
---------------------------------------------------------------------------------------------------------------	
	for(i...) passing rows

		Detecting max value of j.
		Storing row index of max value.
end of i
---------------------------------------------------------------------------------------------------------------

	Creating permutation matrix: Row interchange operation at identity matrix between row index of max value and index of pivot location (always j).
	Importat point: Row index of pivot is always equal to j. Because pivot is at the j=i line.
	Multiplying permutation matrix with A matrix.
		
----------------------------------------------------------------------------------------------------------------	
	
	for(i...) passing rows
	
		Creating elemenatry matrix
		Creating inverse of elemenatry matrix
end of i
----------------------------------------------------------------------------
	
	Multiplying elementary matrix with A matrix.

end of j
--------------------------------------------------------------------------------------




Note that I used three dimenisonal matrix to store permutation matrices, elementary matrices and their inverses.






    j=0  j=1  j=2

i=0  a    b    c

i=1  d    e    f

i=2  g    k    l


