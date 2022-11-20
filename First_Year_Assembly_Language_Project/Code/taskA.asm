.data    
#-----------------------------
#This is the data segment
#-----------------------------

strEnterName: .asciiz "Enter your name. \n"
strEnterReg: .asciiz "Enter your student registration number. \n"
strName: .space 30
strNameRes: .asciiz "Your name is: "
strRegRes: .asciiz "Your registration number is: "

.text 
#-------------------------------
#This is the body of the code
#-------------------------------

main:

#
#Output strEnterName
#

li $v0, 4
la $a0, strEnterName
syscall

#
#Accept string input
#

li $v0, 8
li $a1 30
la $a0, strName
syscall

#
#Output strEnterReg
#

li $v0, 4
la $a0, strEnterReg
syscall

#
#Accept integer input
#
li $v0, 5
syscall

#
#Send user integer to $t0
#

move $t0, $v0

#
#Output user's name
#

li $v0, 4
la $a0, strNameRes
syscall
la $a0, strName
syscall

#
#Output user's registration number
#

la $a0, strRegRes
syscall
li $v0, 1
move $a0, $t0
syscall

#
# System call code for exit = 10
#

li $v0, 10
syscall
