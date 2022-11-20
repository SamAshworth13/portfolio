#Attempting Tasks A (Method Invocation) and B (Arithmetic and Logic)
import antlr4 as antlr
from CoffeeLexer import CoffeeLexer
from CoffeeVisitor import CoffeeVisitor
from CoffeeParser import CoffeeParser
from CoffeeUtil import Var, Method, Import, Loop, SymbolTable


class CoffeeTreeVisitor(CoffeeVisitor):
    def __init__(self):
        self.stbl = SymbolTable()
        
    def visitProgram(self, ctx):
        self.stbl.pushFrame(ctx)
        self.visitChildren(ctx)
        self.stbl.popFrame()
        
    def visitBlock(self, ctx):
        if (ctx.LCURLY() != None):
            self.stbl.pushScope()
            
        self.visitChildren(ctx)
        
        if (ctx.LCURLY() != None):
            self.stbl.popScope()
    
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
        
        for i in range(len(ctx.var_assign())):
            var_id = ctx.var_assign(i).var().ID().getText()
            
            var = self.stbl.peek(var_id)
            #if variable already exists, output error message
            if (var != None):
                print("Error on line", line, ": var '", var_id, 
                          "' already declared in scope on line", var.line)
            
            #check if variable is an array
            if (ctx.var_assign(i).var().INT_LIT() != None):
                var_size = 8
                var_array = True
                #if array length is set to 0, output error message
                if (int(ctx.var_assign(i).var().INT_LIT().getText()) == 0):
                    print("Error on line", line, ": array '", var_id, 
                          "' cannot have a length of 0")
            else:
                #check if variable is being assigned a value
                if (ctx.var_assign(i).expr() != None):
                    expr_type = self.visit(ctx.var_assign(i).expr())
                    #check whether assigned value has an existing type
                    if (expr_type != None):
                        #if value type doesn't match variable type, output error message
                        if (expr_type != var_type):
                            print ("Error on line", line, ": var '", var_id, 
                                   "' is of type '", var_type, 
                                   "' so cannot be assigned a value of type, '", expr_type, "'")
                    else:
                        #check if the expression is a method call
                        if (ctx.var_assign(i).expr().method_call() != None):
                            method = ctx.var_assign(i).expr().method_call()
                            method_id = method.ID().getText()
                            method = self.stbl.find(method_id)
                            #check the method exists
                            if (method != None):
                                return_type = method.return_type
                                #if the method doesn't return a value, output error message
                                if (return_type == "void"):
                                    print("Error on line", line, ": method '", method_id, 
                                          "' is a void method, so cannot be used to assign a value")
                                #if method's return type differs from variable type, output error message
                                elif (return_type != var_type):
                                    print("Error on line", line, ": var '", var_id, 
                                          "' is of type '", var_type, 
                                          "' so cannot be assigned a value of type, '", return_type, "'")
                
                var_size = 8
                var_array = False
            #if all is correct, push the variable
            var = Var(var_id, var_type, var_size, var_scope, var_array, line)
            self.stbl.pushVar(var)
    
            
            
    def visitMethod_decl(self, ctx):
        line = ctx.start.line
        method_id = ctx.ID().getText()
        method_type = ctx.return_type().getText()
        
        #if a method with same name already exists, output error message
        method = self.stbl.peek(method_id)
        if (method != None):
            print("Error on line", line, ": method '", method_id, 
                      "' already declared in scope on line", method.line)
        
        #if all is correct, push the method
        method = Method(method_id, method_type, line)
        self.stbl.pushMethod(method)
        self.stbl.pushFrame(method)
        
        
        
        
        for i in range(len(ctx.param())):
            var_id = ctx.param(i).ID().getText()
            var_type = ctx.param(i).data_type().getText()
            
            var_size = 8
            var_array = False
            
            var = self.stbl.peek(var_id)
            #if variable already exists, output error message
            if (var != None):
                print("Error on line", line, ": var '", var_id, 
                      "' already declared in scope on line", var.line)
            
            #if all is correct, push the variable
            var = Var(var_id, var_type, var_size, Var.LOCAL, var_array, line)
            self.stbl.pushVar(var)
            
            method.pushParam(var_type)
            
        self.visit(ctx.block())
        
        if (method.has_return == True):
            #if a void method is trying to return a value output error message
            if (method_type == "void"):
                print("Error on line", line, ": method '", method_id, 
                      "' is a void method, so should not return data")
        else:
            #if a method that should return a value isn't doing so, output error message
            if (method_type != "void"):
                print("Error on line", line, ": method '", method_id, 
                      "' must return a(n) ", method_type, "value")
            
        self.stbl.popFrame()
        
        
    def visitExpr(self, ctx):
        line  = ctx.start.line
        if (ctx.literal() != None):
            return self.visit(ctx.literal())
        elif (ctx.location() != None):
            return self.visit(ctx.location())
        elif (len(ctx.expr()) == 2):
            expr0_type = self.visit(ctx.expr(0))
            expr1_type = self.visit(ctx.expr(1))
            #Return highest precedence type
            if (expr0_type == "float" or expr1_type == "float"):
                return "float"
            elif (expr0_type == "int" or expr1_type == "int"):
                return "int"
            elif (expr0_type == "bool" or expr1_type == "bool"):
                return "bool"
            else:
                return expr0_type
        elif (ctx.data_type() != None):
            return self.visit(ctx.data_type().getText())
        else:
            #if NOT operator is being used on a data type other than a bool, output error message
            if (ctx.NOT != None and self.visitChildren(ctx) != "bool"):
                print ("Error on line", line, "'NOT' operator (!) can only be",
                       "used on 'bool' data type, not '", self.visitChildren(ctx), "'")
            return self.visitChildren(ctx)
        
        
    def visitLiteral(self, ctx):
        if (ctx != None):
            if (ctx.INT_LIT() != None):
                return "int"
            elif (ctx.FLOAT_LIT() != None):
                return "float"
            elif (ctx.bool_lit() != None):
                return "bool"
            elif (ctx.STRING_LIT() != None):
                return "string"
            elif (ctx.CHAR_LIT() != None):
                return "char"
            
            
    def visitLocation(self, ctx):
        if (ctx != None):
            var_id = ctx.ID().getText()
            var = self.stbl.find(var_id)
            var_type = var.data_type
            return var_type
        
    def visitImport_stmt(self, ctx):
        
        if (ctx != None):
            line = ctx.start.line
            for i in range(len(ctx.ID())):
                import_id = ctx.ID(i).getText()
                #check for existing symbol with the same id
                import_symbol = self.stbl.find(import_id)
                
                #if symbol already exists, output error message
                if (import_symbol != None):
                    print("Error on line", line, ": method '", import_id, 
                      "' was already imported")
                else:
                    #if symbol doesn't already exist, add it to stbl
                    import_symbol = Import(import_id, 'int', line)
                    self.stbl.pushMethod(import_symbol)
                    self.stbl.pushFrame(import_symbol)
                
    
    def visitReturn(self, ctx):
        
        if (ctx != None):
            line = ctx.start.line
            method_ctx = self.stbl.getMethodContext()
            #check if method context has the return_type attribute
            if (hasattr(method_ctx, 'return_type')):
                method_ctx.has_return = True
                return_type = method_ctx.return_type
                #if incorrect type is returned, output error message
                if(ctx.expr() != None):
                    expr_type = self.visit(ctx.expr())
                    if (return_type != "void" and expr_type != return_type):
                        print ("Error on line", line, ": method should return type: '", 
                               return_type, "', not type: '", 
                               expr_type, "'")
            else:
                #runs if return is used in the main method
                if(ctx.expr() != None):
                    expr_type = self.visit(ctx.expr())
                    if (expr_type != "int"):
                        print ("Error on line", line, ": main method should only return type: 'int'", 
                               ", not type: '", expr_type, "'")
                
                
    
    def visitMethod_call(self, ctx):
        
        if (ctx != None):
            line = ctx.start.line
            method_id = ctx.ID().getText()
            method = self.stbl.find(method_id)
            
            
            if (method != None):
                #if method was imported, output warning
                if (method.name == "import"):
                    print ("Warning: method '", method_id, "' being called on line", line, 
                           "was imported and has no signature")
                else:
                    #if number of parameters is incorrect, output error message
                    if (len(method.param) != len(ctx.expr())):
                        print ("Error on line", line, ": method '", method_id, 
                               "' was called with the incorrect number of parameters")
                    else:
                        
                        for i in range(len(method.param)):
                            expr_type = self.visit(ctx.expr(i))
                            param_type = method.param[i]
                            
                            #if an expresssion of the wrong type is passed, output error message
                            if (expr_type != param_type):
                                print("Error on line", line, ": a(n)", expr_type, 
                                      "was passed, when a(n)", param_type, "was expected")
                            
            #if method hasn't been declared, output eror message
            else:
                print ("Error on line", line, ": method '", method_id, "' was not declared")
            
    
            
    def visitAssign(self, ctx):
        
        if (ctx != None):
            line = ctx.start.line
            var_type = self.visit(ctx.location())
            expr_type = self.visit(ctx.expr())
            
            #if type of variable doesn't match type of expression, output error message
            if (var_type != expr_type):
                print("Error on line", line, ": type '", expr_type, 
                      "' does not match expected type of '", var_type, "'")
            
                    

    def visitIf(self, ctx):
        
        return_found = False
        correct_return = True
        if (ctx != None):
            line = ctx.start.line
            #loop through each block of if statement
            for i in range(len(ctx.block())):
                #loop through each inner block           
                for j in range(len(ctx.block(i).block())):
                    expr_type = self.visit(ctx.expr())
                    if (expr_type != "bool"):
                        print ("Error on line", line, ": 'if statement' expression must be of type 'bool'",
                               "not '", expr_type, "'")
                    #check if current statement is a return statement
                    if (isinstance(ctx.block(i).block(j).statement(), CoffeeParser.ReturnContext)):
                        #a return statement has been found
                        return_found = True
                        #visit return to check correct type is being returned
                        self.visit(ctx.block(i).block(j).statement())
                        #check if current statement is an if statement
                    elif (isinstance(ctx.block(i).block(j).statement(), CoffeeParser.IfContext)):
                        innerStmt_ctx = ctx.block(i).block(j).statement()
                        #loop through each block of nested if statement
                        for k in range (len(innerStmt_ctx.block())):
                            #loop through each inner block
                            for l in range (len(innerStmt_ctx.block(k).block())):
                                #check if current statement is a return statement
                                if (isinstance(innerStmt_ctx.block(k).block(l).statement(), CoffeeParser.ReturnContext)):
                                    #a return statement has been found in nested if statement
                                    return_found = True
                                    #visit return to check correct type is being returned
                                    self.visit(innerStmt_ctx.block(k).block(l).statement())
                            
            #if a return statement has been found, check that all branches have one            
            if (return_found == True):
                #loop through each block of if statement
                for i in range(len(ctx.block())):
                    #reset return_found to be false
                    return_found = False
                    #loop through each inner block           
                    for j in range(len(ctx.block(i).block())):
                        #check if current statement is a return statement
                        if (isinstance(ctx.block(i).block(j).statement(), CoffeeParser.ReturnContext)):
                            #a return statement has been found
                            return_found = True
                        #check if current statement is an if statement
                        elif (isinstance(ctx.block(i).block(j).statement(), CoffeeParser.IfContext)):
                            #find out if nested if statement has the correct number of returns
                            inner_return = self.visit(ctx.block(i).block(j).statement())
                            if (inner_return == False):
                                correct_return = False
                    if (return_found == False or len(ctx.block()) == 1):
                        correct_return = False
            
            if (correct_return == False):
                print ("Warning: on line", line, "some branches of the 'if statement'",
                       "might not return a value")
        return correct_return
                    
            
        

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
