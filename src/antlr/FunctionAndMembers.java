package antlr;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import antlr.Java8Parser.FieldDeclarationContext;
import antlr.Java8Parser.FormalParameterContext;
import antlr.Java8Parser.LocalVariableDeclarationContext;
import antlr.Java8Parser.ResultContext;

/*
 * check import part to determin the refer for different package, 
 * how about the same package refer? 
 * 
 * get the full name of type
 * */


public class FunctionAndMembers extends Java8BaseListener{
	Set<String> refs;
	Set<String> functions;
	Set<String> imports;
	String packagename;
	String classname;
	String curFunc;
	
	Set<String> nodes;
	Set<String> edges;
	
	public FunctionAndMembers() {
		refs = new HashSet<>(); 
		imports = new HashSet<>();
		functions = new HashSet<>();
		nodes = new HashSet<>();
		edges = new HashSet<>();
	}
	
	
	@Override public void enterUnannType(Java8Parser.UnannTypeContext ctx) { 
		String type;
		if(ctx.unannReferenceType() != null) {
			type = ctx.unannReferenceType().getText();
		}else {
			type = ctx.getText();
		}
		
		//alltypes.add(type);
		//System.out.println("type declar:"+type);
		//System.out.println("type context1:"+ctx.getParent().getClass());
		//if(ctx.getParent().getParent() != null) {
		//	System.out.println("type context2: " + ctx.getParent().getParent().getClass());
		//}
		//System.out.println("type context3: " + ctx.getParent().getParent().getParent().getClass());
		//System.out.println("type context4: " + ctx.getParent().getParent().getParent().getParent().getClass());
		
		if(ctx.getParent() instanceof FieldDeclarationContext) {
			
		}else if(ctx.getParent() instanceof ResultContext) {
			refs.add(ctx.getText());
			//System.out.println("type declar:"+type + " return func "+ this.curFunc);
			nodes.add(ctx.getText());
			edges.add(this.curFunc+"->"+ctx.getText() +"|"+ this.curFunc+"|"+ ctx.getText());
		}else if(ctx.getParent() instanceof FormalParameterContext) {
			refs.add(ctx.getText());
			//System.out.println("type declar:"+type + " parameters func "+ this.curFunc);
			nodes.add(ctx.getText());
			edges.add(this.curFunc+"->"+ctx.getText() +"|"+ this.curFunc+"|"+ ctx.getText());
		}else if(ctx.getParent() instanceof LocalVariableDeclarationContext) {
			refs.add(ctx.getText());
			//System.out.println("type declar:"+type + " in func "+ this.curFunc);
			nodes.add(ctx.getText());
			edges.add(this.curFunc+"->"+ctx.getText() +"|"+ this.curFunc+"|"+ ctx.getText());
		}else {
			
		}
	
		
	}
	@Override public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) { 
		
		packagename=ctx.Identifier().stream().map(t -> t.getText()).collect(Collectors.joining("."));
		//rewriter.replace(ctx.PACKAGE().getSymbol(),ctx.SEMI().getSymbol(),"package k.this.one;");
	}
	@Override public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) { 
		if(ctx.normalClassDeclaration()!=null) {
			classname = ctx.normalClassDeclaration().Identifier().getText();
		}else if(ctx.enumDeclaration() != null) {
			classname = ctx.enumDeclaration().Identifier().getText();
		}
		
		//rewriter.insertAfter(ctx.start, "addthis");
	}
	@Override public void enterInterfaceDeclaration(Java8Parser.InterfaceDeclarationContext ctx) {
		if(ctx.normalInterfaceDeclaration() != null) {
			classname = ctx.normalInterfaceDeclaration().Identifier().getText();
		}else if(ctx.annotationTypeDeclaration() !=null) {
			classname = ctx.annotationTypeDeclaration().Identifier().getText();
		}
	}
	
	@Override public void enterClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) { 
		//System.out.println("classmemberdeclar:" + ctx.getText());
		if(ctx.interfaceDeclaration() != null) {
			
			//System.out.println("interfaceDeclaration:" + ctx.interfaceDeclaration().getText());
		}else if(ctx.classDeclaration() != null) {
			//System.out.println("classDeclaration:" + ctx.classDeclaration().getText());
		}else if(ctx.methodDeclaration() != null) {
			//System.out.println("methodDeclaration:" + ctx.methodDeclaration().getText());
			functions.add(ctx.methodDeclaration().methodHeader().methodDeclarator().Identifier().getText());
		}else if(ctx.fieldDeclaration() != null) {
			//System.out.println("fieldDeclaration:" + ctx.fieldDeclaration().getText());
			refs.add(ctx.fieldDeclaration().variableDeclaratorList().getText());
		}
	}
	
	@Override public void enterExpressionStatement(Java8Parser.ExpressionStatementContext ctx) { 
		//System.out.println("enterExpressionStatement:" + ctx.getText());
		if(ctx.statementExpression().methodInvocation() != null) {
			//System.out.println("methodInvocation:" + ctx.statementExpression().methodInvocation().getText());
			//System.out.println("methodInvocation: in " + this.curFunc);
			//System.out.println("methodInvocation: in " + ctx.statementExpression().methodInvocation().methodName().getText());
			//System.out.println("methodInvocation: in " + ctx.statementExpression().methodInvocation().typeName().getText());
		}else if(ctx.statementExpression().assignment() != null) {
			//System.out.println("assignment:" + ctx.statementExpression().assignment().getText());
		}
	}
	
	@Override public void enterImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {
		if(ctx.singleTypeImportDeclaration() != null) {
			String im =ctx.singleTypeImportDeclaration().typeName().getText();
		
			imports.add(im);
		}else if(ctx.singleStaticImportDeclaration() != null) {
			String im =ctx.singleStaticImportDeclaration().typeName().getText();
		
			imports.add(im);
		}
		
	}
	@Override public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
		this.curFunc= ctx.methodHeader().methodDeclarator().Identifier().getText();
		nodes.add(this.curFunc);
		
	}
	@Override public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
		this.curFunc="";
		
	}
	@Override public void enterInterfaceMethodDeclaration(Java8Parser.InterfaceMethodDeclarationContext ctx) {
		this.curFunc= ctx.methodHeader().methodDeclarator().Identifier().getText();
		nodes.add(this.curFunc);
	}
	
	@Override public void exitInterfaceMethodDeclaration(Java8Parser.InterfaceMethodDeclarationContext ctx) {
		this.curFunc="";
	}
	
	@Override public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
		this.curFunc= ctx.constructorDeclarator().simpleTypeName().getText();
		nodes.add(this.curFunc);
	}

	@Override public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
		this.curFunc="";
	}
	public Set<String> getImports() {
		return imports;
	}
	public void setImports(Set<String> imports) {
		this.imports = imports;
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public String getClassname() {
		return packagename + "."+ classname;
	}


	public Set<String> getRefs() {
		return refs;
	}


	public void setRefs(Set<String> refs) {
		this.refs = refs;
	}


	public Set<String> getFunctions() {
		return functions;
	}


	public void setFunctions(Set<String> functions) {
		this.functions = functions;
	}


	public void setClassname(String classname) {
		this.classname = classname;
	}


	public Set<String> getNodes() {
		return nodes;
	}


	public void setNodes(Set<String> nodes) {
		this.nodes = nodes;
	}


	public Set<String> getEdges() {
		return edges;
	}


	public void setEdges(Set<String> edges) {
		this.edges = edges;
	}
	
	
	
	
}
