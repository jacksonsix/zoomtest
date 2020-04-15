package antlr;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * check import part to determin the refer for different package, 
 * how about the same package refer? 
 * 
 * get the full name of type
 * */


public class RefPath extends Java8BaseListener{
	Set<String> alltypes;
	Set<String> imports;
	String packagename;
	String classname;
	public RefPath() {
		alltypes = new HashSet<>(); 
		imports = new HashSet<>();
	}
	public Collection<String> getResult(){
		Set<String> prim = new HashSet<String>();
		prim.add("String");
		
		return this.alltypes;
	}
	
	@Override public void enterUnannType(Java8Parser.UnannTypeContext ctx) { 
		//String type = ctx.unannReferenceType().getText();
		//alltypes.add(type);
		
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
	@Override public void enterImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {
		if(ctx.singleTypeImportDeclaration() != null) {
			String im =ctx.singleTypeImportDeclaration().typeName().getText();
			//System.out.println(im);
			imports.add(im);
		}else if(ctx.singleStaticImportDeclaration() != null) {
			String im =ctx.singleStaticImportDeclaration().typeName().getText();
			//System.out.println(im);
			imports.add(im);
		}
		
	}
	public Set<String> getAlltypes() {
		return alltypes;
	}
	public void setAlltypes(Set<String> alltypes) {
		this.alltypes = alltypes;
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
	
	
	
	
}
