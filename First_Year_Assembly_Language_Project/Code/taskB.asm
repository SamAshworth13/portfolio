.data    
#-----------------------------
#This is the data segment
#-----------------------------
strNums: .asciiz "Enter the first two digits of your student registration number. \n"
strResult: .asciiz " divided by 2 (as a quotient) is: "
strError: .asciiz "\n You must enter exactly two digits! \n"

.text 
#-------------------------------
#This is the body of the code
#-------------------------------

main:

#
#Output strNums
#

li $v0, 4
la $a0, strNums
syscall

#
#Accept integer input
#

li $v0, 5
syscall

hiCheck:

#
#Check if the program has less than 3 digits then branch to next check
#

ble $v0, 99, lowCheck
b invalidNum
lowCheck:

#
#Check if the program has more than 1 digit then branch to continue program
#

bge $v0, 10, validNum

invalidNum:
#
#Output strError and restart program
#

li $v0, 4
la $a0, strError
syscall
b main

validNum:

#
#Send user integer to $t0
#

move $t0, $v0

#
#Divide user integer by 2 and store result in $t1
#

div $t1, $t0, 2

#
#Output user integer
#

li $v0, 1
move $a0, $t0
syscall

#
#Output strResult
#

li $v0, 4
la $a0, strResult
syscall

#
#Output result of division
#

li $v0, 1
move $a0, $t1
syscall

#
# System call code for exit = 10
#

li $v0, 10
syscall
