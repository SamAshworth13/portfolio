.data    
#-----------------------------
#This is the data segment
#-----------------------------
strNums: .asciiz "Enter a four-digit number. \n"
strResult: .asciiz " divided by 3.3 is: "
strError: .asciiz "\nYou must enter exactly four digits! \n"
num1: .float 3.3

.text 
#-------------------------------
#This is the body of the code
#-------------------------------

main:

#
#Load 3.3 into Coprocessor 1 at $f4 as a single
#

l.s $f4, num1

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

ble $v0, 9999, lowCheck
b invalidNum

lowCheck:

#
#Check if the program has more than 1 digit then branch to continue program
#

bge $v0, 1000, validNum

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
#Send user integer to $f0 in Coprocessor1
#

mtc1 $v0, $f0

#
#Convert user integer to a single
#

cvt.s.w $f0, $f0

#
#Divide user single by 3.3 and store result in $f12
#

div.s $f12, $f0, $f4

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
#Output result of division as a single
#

li $v0, 2
syscall

#
# System call code for exit = 10
#

li $v0, 10
syscall

