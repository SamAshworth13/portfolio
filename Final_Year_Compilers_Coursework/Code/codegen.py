#Attempting Tasks A (Expressions) and C (Loops)
import antlr4 as antlr
from CoffeeLexer import CoffeeLexer
from CoffeeParser import CoffeeParser
from CoffeeVisitor import CoffeeVisitor
from CoffeeUtil import Var, Method, Import, Loop, SymbolTable

class CoffeeTreeVisitor(CoffeeVisitor):
    def __init__(self):
        self.stbl = SymbolTable()
        self.data = '.data\n'
        self.body = '.text\n.global main\n'

    def visitProgram(self, ctx):
        line = ctx.start.line

        method = Method('main', 'int', line)

        self.stbl.pushFrame(method)
        
        self.stbl.pushMethod(method)

        method.body += method.id + ':\n'
        method.body += '  push %rbp\n'
        method.body += '  movq %rsp, %rbp\n'

        self.visitChildren(ctx)

        if method.has_return == False:
            method.body += '  pop %rbp\n'
            method.body += '  ret\n'

        self.data += method.data
        self.body += method.body

        self.stbl.popFrame()


    def visitMethod_decl(self, ctx):
        
        #define method
        line = ctx.start.line
        method_id = ctx.ID().getText()
        return_type = ctx.return_type().getText()
        
        method = Method(method_id, return_type, line)
        
        #push method and frame
        self.stbl.pushMethod(method)
        self.stbl.pushFrame(method)
        
        #generate basic method template assembly code
        method.body += method.id + ':\n'
        method.body += '  push %rbp\n'
        method.body += '  movq %rsp, %rbp\n'
        
        #loop through parameters
        for i in range(len(ctx.param())):
            #define var
            var_id = ctx.param(i).ID().getText()
            var_type = ctx.param(i).data_type().getText()
            var_size = 8
            var_array = False
            var_scope = Var.LOCAL
            var = Var(var_id, var_type, var_size, var_scope, var_array, line)
            
            #push var to stbl
            self.stbl.pushVar(var)
            #push param of correct variable type
            method.pushParam(var_type)
            
            #create assembly code to store parameters in memory addresses relative to base pointer
            method.body += '  movq ' + self.stbl.param_reg[i] + ', ' + str(var.addr) + '(%rbp)\n'
            
        
        #if block follows method_decl, visit block, otherwise, visit expr
        if ctx.block() != None:
            self.visit(ctx.block())
        else:
            self.visit(ctx.expr())
        
        if method.has_return == False:
            method.body += '  pop %rbp\n'
            method.body += '  ret\n'
         
        #concatenate method body string to main code
        self.data += method.data
        self.body += method.body

        self.stbl.popFrame()
        
                
        
    def visitBlock(self, ctx): 
        if (ctx.LCURLY() != None):
            self.stbl.pushScope()
            
        self.visitChildren(ctx)
        
        if (ctx.LCURLY() != None):
            self.stbl.popScope()
        
    def visitLiteral(self, ctx):
        method_ctx = self.stbl.getMethodContext()
        if (ctx.INT_LIT() != None):
            method_ctx.body += '  movq $' +ctx.INT_LIT().getText() + ', %rax\n'
            
    def visitExpr(self, ctx):
        if (ctx.literal() != None): 
            self.visit(ctx.literal())
        elif (ctx.location() != None):
            self.visit(ctx.location())
        elif (len(ctx.expr()) == 2): 
            method_ctx = self.stbl.getMethodContext() 
             
            self.visit(ctx.expr(0)) 
            
            #allocate 8 bytes on stack
            self.stbl.pushBytes(8)
            #temp save rax to stack
            method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rsp)\n'
 
            self.visit(ctx.expr(1)) 
            #move the result of expr(1) to %r11  
            method_ctx.body += '  movq %rax, %r11\n'
            
            #recover temp from stack to r10
            method_ctx.body += '  movq ' + str(self.stbl.getStackPtr()) + '(%rsp), %r10\n'
            #de-allocate 8 bytes from stack
            self.stbl.popBytes(8)
            
            if (ctx.ADD() != None): 
                #do the add operation on %r10 and %r11 
                method_ctx.body += '  addq %r10, %r11\n'
                #move the result to %rax 
                method_ctx.body += '  movq %r11, %rax\n'
            elif (ctx.MUL() != None):
                #do the multiply operation on %r10 and %r11
                method_ctx.body += '  imul %r10, %r11\n'
                #move the result to %rax 
                method_ctx.body += '  movq %r11, %rax\n'
            elif (ctx.SUB() != None):
                #do the subtract operation on %r10 and %r11
                method_ctx.body += '  subq %r11, %r10\n'
                #move the result to %rax 
                method_ctx.body += '  movq %r10, %rax\n'
            elif (ctx.MOD() != None):
                #do the division operation on %r10 and %r11
                method_ctx.body += '  movq $0, %rdx\n'  
                method_ctx.body += '  movq %r11, %rbx\n'
                method_ctx.body += '  movq %r10, %rax\n'
                method_ctx.body += '  idiv %rbx\n'
                #move the remainder to %rax 
                method_ctx.body += '  movq %rdx, %rax\n'
        elif (ctx.SUB() != None):
            method_ctx = self.stbl.getMethodContext()
            #visit the expression to be negated
            self.visitChildren(ctx)
            #negate the expression
            method_ctx.body += '  negq %rax\n'
        else: 
            self.visitChildren(ctx)
        
    def visitLocation(self, ctx):
        
        method_ctx = self.stbl.getMethodContext()
        var_id = ctx.ID().getText()
        var = self.stbl.find(var_id)
        
        if (var.scope == Var.GLOBAL):
            method_ctx.body += '  movq ' + var_id + '(%rip), %rax\n'
        elif (var.scope == Var.LOCAL):
            method_ctx.body += '  movq ' + str(var.addr) + '(%rbp), %rax\n'
                

    
    def visitMethod_call(self, ctx):
        method_ctx = self.stbl.getMethodContext() 
        
        method_id = ctx.ID().getText()
        
        for i in range(len(ctx.expr())): 
            #evaluate expression 
            self.visit(ctx.expr(i))
            #move result of expr(i) to param register
            method_ctx.body += '  movq %rax, ' + self.stbl.param_reg[i] + '\n'
        #add stbl.getStackPointer() to %rsp 
        method_ctx.body += '  addq $' + str(self.stbl.getStackPtr()) + ', %rsp\n'
        #make call to method
        method_ctx.body += '  call ' + method_id + '\n'
        #subtract stbl.getStackPointer() from %rsp
        method_ctx.body += '  subq $' + str(self.stbl.getStackPtr()) + ', %rsp\n'
    
    
        #visit a global variable declaration node
    def visitGlobal_decl(self, ctx):
        self.declareVar(ctx.var_decl(), Var.GLOBAL)
        
    #visit a local variable declaration node
    def visitVar_decl(self, ctx):
        self.declareVar(ctx, Var.LOCAL)
       
    #method for visiting nodes for both local and global variable declarations
    def declareVar(self, ctx, var_scope):
        line = ctx.start.line
        var_type = ctx.data_type().getText()
        method_ctx = self.stbl.getMethodContext()
        
        for i in range(len(ctx.var_assign())):
            var_id = ctx.var_assign(i).var().ID().getText()
                        
            #check if variable is an array
            if (ctx.var_assign(i).var().INT_LIT() != None):
                var_size = 8
                var_array = True
            else:
                var_size = 8
                var_array = False
                #check if variable is being assigned a value
                if (ctx.var_assign(i).expr() != None):
                    #visit the expression
                    self.visit(ctx.var_assign(i).expr())
                    #assign the result of the expression (%rax) to the variable
                    method_ctx.body += '  movq %rax, ' + var_id + '(%rip)\n'
            
            if (var_scope == Var.GLOBAL):
                method_ctx.data += '  .comm ' + var_id + ',' + str(var_size) + '\n'
            #if all is correct, push the variable
            var = Var(var_id, var_type, var_size, var_scope, var_array, line)
            self.stbl.pushVar(var)
            
    def visitAssign(self, ctx):
        method_ctx = self.stbl.getMethodContext()
        #find id of variable
        var_id = ctx.location().ID().getText()
        #visit the expression related to the assign
        self.visit(ctx.expr())
        #assign the result of the expression (%rax) to the variable
        method_ctx.body += '  movq %rax, ' + var_id + '(%rip)\n'
        
        
    def visitFor(self, ctx):
        line = ctx.start.line
        method_ctx = self.stbl.getMethodContext()
        
        start_label = self.stbl.getNextLabel()
        mid_label = self.stbl.getNextLabel()
        end_label = self.stbl.getNextLabel()
        
        self.stbl.pushScope()
        
        loop_var_id = ctx.loop_var().getText()
        loop_var = Var(loop_var_id,'int',8,Var.LOCAL,False,line)
        
        self.stbl.pushVar(loop_var)
        
        #create and initialise low variable
        low_var = ctx.limit().low().getText()
        self.stbl.pushBytes(8)
        method_ctx.body += '  movq $' + low_var + ', %rax\n'
        method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rbp)\n'
        
        #create and initialise high variable
        high_var = ctx.limit().high().getText()
        self.stbl.pushBytes(8)
        method_ctx.body += '  movq $' + high_var + ', %rax\n'
        method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rbp)\n'
        
        #create and initialise step variable
        step_var = ctx.limit().step().getText()
        self.stbl.pushBytes(8)
        method_ctx.body += '  movq $' + step_var + ', %rax\n'
        method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rbp)\n'
        
        #initialise loop variable
        self.stbl.popBytes(16)
        method_ctx.body += '  movq ' + str(self.stbl.getStackPtr()) + '(%rbp), %rax\n'
        self.stbl.popBytes(8)
        method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rbp)\n'
        self.stbl.pushBytes(24)
        
        method_ctx.body += start_label + ':\n'
        self.visit(ctx.block())
        method_ctx.body += mid_label + ':\n'
        #increment loop variable
        self.stbl.popBytes(24)
        method_ctx.body += '  movq ' + str(self.stbl.getStackPtr()) + '(%rbp), %rax\n'
        self.stbl.pushBytes(24)
        method_ctx.body += '  movq ' + str(self.stbl.getStackPtr()) + '(%rbp), %r11\n'
        method_ctx.body += '  addq %r11, %rax\n'
        
        
        
        #check loop termination criterion
        self.stbl.popBytes(24)
        method_ctx.body += '  movq %rax, ' + str(self.stbl.getStackPtr()) + '(%rbp)\n'
        self.stbl.pushBytes(16)
        method_ctx.body += '  movq ' + str(self.stbl.getStackPtr()) + '(%rbp), %r10\n'
        method_ctx.body += '  cmp %r10, %rax\n'
        method_ctx.body += '  jge .LC2\n'
        method_ctx.body += '  jmp .LC0\n'
        
        method_ctx.body += end_label + ':\n'
        
        
#load source code
filein = open('./test.coffee', 'r')
source_code = filein.read();
filein.close()

#create a token stream from source code
lexer = CoffeeLexer(antlr.InputStream(source_code))
stream = antlr.CommonTokenStream(lexer)

#parse token stream
parser = CoffeeParser(stream)
tree = parser.program()

#create Coffee Visitor object
visitor = CoffeeTreeVisitor()

#visit nodes from tree root
visitor.visit(tree)

#assembly output code
code = visitor.data + visitor.body
print(code)

#save the assembly file
fileout = open('a.s', 'w')
fileout.write(code)
fileout.close()

#assemble and link
import os
os.system("gcc a.s -lm ; ./a.out ; echo $?")
