package antlr;

import java.util.Collection;
import java.util.LinkedList;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import antlr.Java8Parser.MethodNameContext;

public class UpdateCode extends Java8BaseListener {
    Collection<String> methods;
    TokenStreamRewriter rewriter;
    public UpdateCode(TokenStream tokens) {
    	 methods = new LinkedList<String>();
    	 rewriter = new TokenStreamRewriter(tokens);
    }
    
	
	
	public Collection<String> getResult(){
		return this.methods;
	}
	@Override 
	public void exitType(Java8Parser.TypeContext ctx) { 
		methods.add(ctx.getText());
	}
	
	@Override public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) { 
		//ctx.getRuleContext().
		methods.add(ctx.normalClassDeclaration().Identifier().getText());
		//rewriter.insertAfter(ctx.start, "addthis");
	}
	
	@Override public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) { 
		methods.add(ctx.methodHeader().methodDeclarator().Identifier().getText());
		//rewriter.insertBefore(ctx.methodHeader().methodDeclarator().Identifier().getSymbol(), "hello");
		
	}
	
	@Override public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) { 
		
		rewriter.replace(ctx.PACKAGE().getSymbol(),ctx.SEMI().getSymbol(),"package k.this.one;");
	}
	
	@Override public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) { 
		rewriter.replace(ctx.constructorDeclarator().simpleTypeName().start, "NewStockEvent");
	}
	
	@Override public void enterUnannType(Java8Parser.UnannTypeContext ctx) { 
		String type = ctx.unannReferenceType().getText();
		if(type.equals("StockEvent")) {
			rewriter.replace(ctx.unannReferenceType().start, "NewStockEvent");
		}
		
	}
	
	@Override public void enterTypeDeclaration(Java8Parser.TypeDeclarationContext ctx) { 
		rewriter.replace(ctx.classDeclaration().normalClassDeclaration().Identifier().getSymbol(),"NewStockEvent");
	}

}
