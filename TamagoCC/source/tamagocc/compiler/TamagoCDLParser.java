// $ANTLR 3.4 C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g 2013-02-17 10:25:39

package tamagocc.compiler;

import tamagocc.api.*;
import tamagocc.impl.*;
import java.util.Collection;
import java.util.ArrayList;
import tamagocc.util.Triplet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class TamagoCDLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDOP", "COMMENT", "ESC_SEQ", "EXPONENT", "FLOAT", "HEX_DIGIT", "ID", "INT", "MULTOP", "NOTOPERATOR", "OCTAL_ESC", "QUANTIFIER", "RELOP", "STRING", "UNICODE_ESC", "WS", "'#'", "'&&'", "'('", "')'", "'*'", "','", "'.'", "'.*'", "':'", "';'", "'@pre'", "'@return'", "'String'", "'['", "'[]'", "']'", "'allow'", "'as'", "'behavior'", "'bool'", "'boolean'", "'coll'", "'component'", "'default'", "'double'", "'fail'", "'false'", "'from'", "'id'", "'implements'", "'in'", "'include'", "'int'", "'invariant'", "'method'", "'module'", "'nil'", "'null'", "'percolator'", "'post'", "'pre'", "'property'", "'provide'", "'read'", "'readwrite'", "'real'", "'refine'", "'require'", "'service'", "'set'", "'state'", "'states'", "'string'", "'this'", "'to'", "'transition'", "'transitions'", "'true'", "'using'", "'void'", "'when'", "'with'", "'write'", "'{'", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int ADDOP=4;
    public static final int COMMENT=5;
    public static final int ESC_SEQ=6;
    public static final int EXPONENT=7;
    public static final int FLOAT=8;
    public static final int HEX_DIGIT=9;
    public static final int ID=10;
    public static final int INT=11;
    public static final int MULTOP=12;
    public static final int NOTOPERATOR=13;
    public static final int OCTAL_ESC=14;
    public static final int QUANTIFIER=15;
    public static final int RELOP=16;
    public static final int STRING=17;
    public static final int UNICODE_ESC=18;
    public static final int WS=19;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public TamagoCDLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public TamagoCDLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return TamagoCDLParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g"; }


    public static class tamagoEntity_return extends ParserRuleReturnScope {
        public TTamagoEntity value;
        public String mod;
        public Collection<TNamespace> uses;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tamagoEntity"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:34:1: tamagoEntity returns [TTamagoEntity value, String mod, Collection<TNamespace> uses] : m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity ) ;
    public final TamagoCDLParser.tamagoEntity_return tamagoEntity() throws RecognitionException {
        TamagoCDLParser.tamagoEntity_return retval = new TamagoCDLParser.tamagoEntity_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.moduleDeclaration_return m =null;

        TamagoCDLParser.usingDeclaration_return u =null;

        TamagoCDLParser.serviceEntity_return s =null;

        TamagoCDLParser.componentEntity_return c =null;



         retval.uses = new ArrayList<TNamespace>(); 
        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:39:2: (m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity ) )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:39:4: m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_moduleDeclaration_in_tamagoEntity80);
            m=moduleDeclaration();

            state._fsp--;

            adaptor.addChild(root_0, m.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:40:2: (u= usingDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==78) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:40:3: u= usingDeclaration
            	    {
            	    pushFollow(FOLLOW_usingDeclaration_in_tamagoEntity86);
            	    u=usingDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, u.getTree());

            	     retval.uses.add((u!=null?u.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:41:2: (s= serviceEntity |c= componentEntity )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==68) ) {
                alt2=1;
            }
            else if ( (LA2_0==42) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:41:3: s= serviceEntity
                    {
                    pushFollow(FOLLOW_serviceEntity_in_tamagoEntity96);
                    s=serviceEntity();

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());

                     retval.mod =(m!=null?m.value:null); retval.value =(s!=null?s.value:null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:42:4: c= componentEntity
                    {
                    pushFollow(FOLLOW_componentEntity_in_tamagoEntity105);
                    c=componentEntity();

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());

                     retval.mod =(m!=null?m.value:null); retval.value =(c!=null?c.value:null); 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


             retval.value = TamagoCDLEaseFactory.entity(retval.mod,retval.uses,retval.value);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "tamagoEntity"


    public static class moduleDeclaration_return extends ParserRuleReturnScope {
        public String value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "moduleDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:45:1: moduleDeclaration returns [ String value ] : 'module' ^s= qualifiedName ';' ;
    public final TamagoCDLParser.moduleDeclaration_return moduleDeclaration() throws RecognitionException {
        TamagoCDLParser.moduleDeclaration_return retval = new TamagoCDLParser.moduleDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal1=null;
        Token char_literal2=null;
        TamagoCDLParser.qualifiedName_return s =null;


        CommonTree string_literal1_tree=null;
        CommonTree char_literal2_tree=null;

         retval.value = ""; 
        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:47:2: ( 'module' ^s= qualifiedName ';' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:47:4: 'module' ^s= qualifiedName ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal1=(Token)match(input,55,FOLLOW_55_in_moduleDeclaration129); 
            string_literal1_tree = 
            (CommonTree)adaptor.create(string_literal1)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal1_tree, root_0);


            pushFollow(FOLLOW_qualifiedName_in_moduleDeclaration134);
            s=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());

            char_literal2=(Token)match(input,29,FOLLOW_29_in_moduleDeclaration136); 
            char_literal2_tree = 
            (CommonTree)adaptor.create(char_literal2)
            ;
            adaptor.addChild(root_0, char_literal2_tree);


             retval.value =(s!=null?s.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "moduleDeclaration"


    public static class qualifiedName_return extends ParserRuleReturnScope {
        public String value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualifiedName"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:50:1: qualifiedName returns [String value ] : s= ID ( '.' ^d= ID )* ;
    public final TamagoCDLParser.qualifiedName_return qualifiedName() throws RecognitionException {
        TamagoCDLParser.qualifiedName_return retval = new TamagoCDLParser.qualifiedName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token s=null;
        Token d=null;
        Token char_literal3=null;

        CommonTree s_tree=null;
        CommonTree d_tree=null;
        CommonTree char_literal3_tree=null;


        	retval.value ="";

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:54:2: (s= ID ( '.' ^d= ID )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:54:4: s= ID ( '.' ^d= ID )*
            {
            root_0 = (CommonTree)adaptor.nil();


            s=(Token)match(input,ID,FOLLOW_ID_in_qualifiedName161); 
            s_tree = 
            (CommonTree)adaptor.create(s)
            ;
            adaptor.addChild(root_0, s_tree);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:54:9: ( '.' ^d= ID )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==26) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:54:10: '.' ^d= ID
            	    {
            	    char_literal3=(Token)match(input,26,FOLLOW_26_in_qualifiedName164); 
            	    char_literal3_tree = 
            	    (CommonTree)adaptor.create(char_literal3)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal3_tree, root_0);


            	    d=(Token)match(input,ID,FOLLOW_ID_in_qualifiedName169); 
            	    d_tree = 
            	    (CommonTree)adaptor.create(d)
            	    ;
            	    adaptor.addChild(root_0, d_tree);


            	    retval.value = "."+(d!=null?d.getText():null)+retval.value; 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


             retval.value =(s!=null?s.getText():null)+retval.value;  

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "qualifiedName"


    public static class qualifiedNameWithWildCard_return extends ParserRuleReturnScope {
        public String value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualifiedNameWithWildCard"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:57:1: qualifiedNameWithWildCard returns [String value] : (s= ID ( '.' d= ID )* ( '.*' )? ) ;
    public final TamagoCDLParser.qualifiedNameWithWildCard_return qualifiedNameWithWildCard() throws RecognitionException {
        TamagoCDLParser.qualifiedNameWithWildCard_return retval = new TamagoCDLParser.qualifiedNameWithWildCard_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token s=null;
        Token d=null;
        Token char_literal4=null;
        Token string_literal5=null;

        CommonTree s_tree=null;
        CommonTree d_tree=null;
        CommonTree char_literal4_tree=null;
        CommonTree string_literal5_tree=null;

         retval.value =""; 
        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:2: ( (s= ID ( '.' d= ID )* ( '.*' )? ) )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:4: (s= ID ( '.' d= ID )* ( '.*' )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:4: (s= ID ( '.' d= ID )* ( '.*' )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:6: s= ID ( '.' d= ID )* ( '.*' )?
            {
            s=(Token)match(input,ID,FOLLOW_ID_in_qualifiedNameWithWildCard200); 
            s_tree = 
            (CommonTree)adaptor.create(s)
            ;
            adaptor.addChild(root_0, s_tree);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:11: ( '.' d= ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==26) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:59:12: '.' d= ID
            	    {
            	    char_literal4=(Token)match(input,26,FOLLOW_26_in_qualifiedNameWithWildCard203); 
            	    char_literal4_tree = 
            	    (CommonTree)adaptor.create(char_literal4)
            	    ;
            	    adaptor.addChild(root_0, char_literal4_tree);


            	    d=(Token)match(input,ID,FOLLOW_ID_in_qualifiedNameWithWildCard207); 
            	    d_tree = 
            	    (CommonTree)adaptor.create(d)
            	    ;
            	    adaptor.addChild(root_0, d_tree);


            	     retval.value =retval.value+"."+(d!=null?d.getText():null); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:60:2: ( '.*' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==27) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:60:3: '.*'
                    {
                    string_literal5=(Token)match(input,27,FOLLOW_27_in_qualifiedNameWithWildCard217); 
                    string_literal5_tree = 
                    (CommonTree)adaptor.create(string_literal5)
                    ;
                    adaptor.addChild(root_0, string_literal5_tree);


                    retval.value =retval.value+".*"; 

                    }
                    break;

            }


            }


             retval.value =(s!=null?s.getText():null)+retval.value; 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "qualifiedNameWithWildCard"


    public static class usingDeclaration_return extends ParserRuleReturnScope {
        public TNamespace value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "usingDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:63:1: usingDeclaration returns [TNamespace value] : 'using' ^p= qualifiedNameWithWildCard ';' !;
    public final TamagoCDLParser.usingDeclaration_return usingDeclaration() throws RecognitionException {
        TamagoCDLParser.usingDeclaration_return retval = new TamagoCDLParser.usingDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal6=null;
        Token char_literal7=null;
        TamagoCDLParser.qualifiedNameWithWildCard_return p =null;


        CommonTree string_literal6_tree=null;
        CommonTree char_literal7_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:64:2: ( 'using' ^p= qualifiedNameWithWildCard ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:64:4: 'using' ^p= qualifiedNameWithWildCard ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal6=(Token)match(input,78,FOLLOW_78_in_usingDeclaration241); 
            string_literal6_tree = 
            (CommonTree)adaptor.create(string_literal6)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal6_tree, root_0);


            pushFollow(FOLLOW_qualifiedNameWithWildCard_in_usingDeclaration246);
            p=qualifiedNameWithWildCard();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());

            char_literal7=(Token)match(input,29,FOLLOW_29_in_usingDeclaration248); 

             retval.value = new TINamespace((p!=null?p.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "usingDeclaration"


    public static class percolator_return extends ParserRuleReturnScope {
        public TPercolator value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "percolator"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:67:1: percolator returns [TPercolator value] : 'percolator' ^ ( '*' | ID ) ';' !;
    public final TamagoCDLParser.percolator_return percolator() throws RecognitionException {
        TamagoCDLParser.percolator_return retval = new TamagoCDLParser.percolator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal8=null;
        Token char_literal9=null;
        Token ID10=null;
        Token char_literal11=null;

        CommonTree string_literal8_tree=null;
        CommonTree char_literal9_tree=null;
        CommonTree ID10_tree=null;
        CommonTree char_literal11_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:68:3: ( 'percolator' ^ ( '*' | ID ) ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:68:3: 'percolator' ^ ( '*' | ID ) ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal8=(Token)match(input,58,FOLLOW_58_in_percolator265); 
            string_literal8_tree = 
            (CommonTree)adaptor.create(string_literal8)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal8_tree, root_0);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:68:17: ( '*' | ID )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==24) ) {
                alt6=1;
            }
            else if ( (LA6_0==ID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:68:18: '*'
                    {
                    char_literal9=(Token)match(input,24,FOLLOW_24_in_percolator269); 
                    char_literal9_tree = 
                    (CommonTree)adaptor.create(char_literal9)
                    ;
                    adaptor.addChild(root_0, char_literal9_tree);


                     retval.value = TIPercolator.getAllPercolator(); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:69:3: ID
                    {
                    ID10=(Token)match(input,ID,FOLLOW_ID_in_percolator275); 
                    ID10_tree = 
                    (CommonTree)adaptor.create(ID10)
                    ;
                    adaptor.addChild(root_0, ID10_tree);


                     retval.value = new TIPercolator((ID10!=null?ID10.getText():null)); 

                    }
                    break;

            }


            char_literal11=(Token)match(input,29,FOLLOW_29_in_percolator280); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "percolator"


    public static class require_return extends ParserRuleReturnScope {
        public TRequire value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "require"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:73:1: require returns [TRequire value] : 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !;
    public final TamagoCDLParser.require_return require() throws RecognitionException {
        TamagoCDLParser.require_return retval = new TamagoCDLParser.require_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token l=null;
        Token string_literal12=null;
        Token string_literal13=null;
        Token string_literal14=null;
        Token string_literal15=null;
        Token char_literal16=null;
        TamagoCDLParser.qualifiedName_return q =null;


        CommonTree n_tree=null;
        CommonTree l_tree=null;
        CommonTree string_literal12_tree=null;
        CommonTree string_literal13_tree=null;
        CommonTree string_literal14_tree=null;
        CommonTree string_literal15_tree=null;
        CommonTree char_literal16_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:74:3: ( 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:74:3: 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal12=(Token)match(input,67,FOLLOW_67_in_require295); 
            string_literal12_tree = 
            (CommonTree)adaptor.create(string_literal12)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal12_tree, root_0);


            string_literal13=(Token)match(input,68,FOLLOW_68_in_require298); 

            n=(Token)match(input,ID,FOLLOW_ID_in_require303); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            string_literal14=(Token)match(input,50,FOLLOW_50_in_require305); 

            pushFollow(FOLLOW_qualifiedName_in_require310);
            q=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, q.getTree());

            string_literal15=(Token)match(input,37,FOLLOW_37_in_require312); 

            l=(Token)match(input,ID,FOLLOW_ID_in_require317); 
            l_tree = 
            (CommonTree)adaptor.create(l)
            ;
            adaptor.addChild(root_0, l_tree);


            char_literal16=(Token)match(input,29,FOLLOW_29_in_require319); 

             retval.value = TamagoCDLEaseFactory.require((n!=null?n.getText():null),(q!=null?q.value:null),(l!=null?l.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "require"


    public static class provide_return extends ParserRuleReturnScope {
        public TProvide value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "provide"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:79:1: provide returns [TProvide value] : 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !;
    public final TamagoCDLParser.provide_return provide() throws RecognitionException {
        TamagoCDLParser.provide_return retval = new TamagoCDLParser.provide_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal17=null;
        Token string_literal18=null;
        Token string_literal19=null;
        Token char_literal20=null;
        TamagoCDLParser.qualifiedName_return q =null;


        CommonTree n_tree=null;
        CommonTree string_literal17_tree=null;
        CommonTree string_literal18_tree=null;
        CommonTree string_literal19_tree=null;
        CommonTree char_literal20_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:80:3: ( 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:80:3: 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal17=(Token)match(input,62,FOLLOW_62_in_provide336); 
            string_literal17_tree = 
            (CommonTree)adaptor.create(string_literal17)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal17_tree, root_0);


            string_literal18=(Token)match(input,68,FOLLOW_68_in_provide339); 

            n=(Token)match(input,ID,FOLLOW_ID_in_provide344); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            string_literal19=(Token)match(input,50,FOLLOW_50_in_provide346); 

            pushFollow(FOLLOW_qualifiedName_in_provide351);
            q=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, q.getTree());

            char_literal20=(Token)match(input,29,FOLLOW_29_in_provide353); 

             retval.value = TamagoCDLEaseFactory.provide((n!=null?n.getText():null),(q!=null?q.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "provide"


    public static class serviceEntity_return extends ParserRuleReturnScope {
        public TService value;
        public TBehavior beh;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "serviceEntity"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:84:1: serviceEntity returns [TService value, TBehavior beh] : 'service' ^label= ID '{' !impls= listimplements refs= listrefine incs= listinclude props= listproperties invs= listinvariants meths= listmethods (b= behaviorDeclaration )? '}' !;
    public final TamagoCDLParser.serviceEntity_return serviceEntity() throws RecognitionException {
        TamagoCDLParser.serviceEntity_return retval = new TamagoCDLParser.serviceEntity_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token label=null;
        Token string_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        TamagoCDLParser.listimplements_return impls =null;

        TamagoCDLParser.listrefine_return refs =null;

        TamagoCDLParser.listinclude_return incs =null;

        TamagoCDLParser.listproperties_return props =null;

        TamagoCDLParser.listinvariants_return invs =null;

        TamagoCDLParser.listmethods_return meths =null;

        TamagoCDLParser.behaviorDeclaration_return b =null;


        CommonTree label_tree=null;
        CommonTree string_literal21_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal23_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:85:2: ( 'service' ^label= ID '{' !impls= listimplements refs= listrefine incs= listinclude props= listproperties invs= listinvariants meths= listmethods (b= behaviorDeclaration )? '}' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:86:3: 'service' ^label= ID '{' !impls= listimplements refs= listrefine incs= listinclude props= listproperties invs= listinvariants meths= listmethods (b= behaviorDeclaration )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal21=(Token)match(input,68,FOLLOW_68_in_serviceEntity373); 
            string_literal21_tree = 
            (CommonTree)adaptor.create(string_literal21)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal21_tree, root_0);


            label=(Token)match(input,ID,FOLLOW_ID_in_serviceEntity378); 
            label_tree = 
            (CommonTree)adaptor.create(label)
            ;
            adaptor.addChild(root_0, label_tree);


            char_literal22=(Token)match(input,83,FOLLOW_83_in_serviceEntity380); 

            pushFollow(FOLLOW_listimplements_in_serviceEntity387);
            impls=listimplements();

            state._fsp--;

            adaptor.addChild(root_0, impls.getTree());

            pushFollow(FOLLOW_listrefine_in_serviceEntity393);
            refs=listrefine();

            state._fsp--;

            adaptor.addChild(root_0, refs.getTree());

            pushFollow(FOLLOW_listinclude_in_serviceEntity399);
            incs=listinclude();

            state._fsp--;

            adaptor.addChild(root_0, incs.getTree());

            pushFollow(FOLLOW_listproperties_in_serviceEntity405);
            props=listproperties();

            state._fsp--;

            adaptor.addChild(root_0, props.getTree());

            pushFollow(FOLLOW_listinvariants_in_serviceEntity411);
            invs=listinvariants();

            state._fsp--;

            adaptor.addChild(root_0, invs.getTree());

            pushFollow(FOLLOW_listmethods_in_serviceEntity417);
            meths=listmethods();

            state._fsp--;

            adaptor.addChild(root_0, meths.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:94:3: (b= behaviorDeclaration )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==38) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:94:4: b= behaviorDeclaration
                    {
                    pushFollow(FOLLOW_behaviorDeclaration_in_serviceEntity426);
                    b=behaviorDeclaration();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.beh =(b!=null?b.value:null); 

                    }
                    break;

            }


            char_literal23=(Token)match(input,85,FOLLOW_85_in_serviceEntity434); 


            		retval.value = TamagoCDLEaseFactory.service(retval.value,(label!=null?label.getText():null),(impls!=null?impls.value:null),(refs!=null?refs.value:null),(incs!=null?incs.value:null),(props!=null?props.value:null),(invs!=null?invs.value:null),(meths!=null?meths.value:null),retval.beh);
            		 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "serviceEntity"


    public static class listimplements_return extends ParserRuleReturnScope {
        public Collection<TImplements> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listimplements"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:101:1: listimplements returns [Collection<TImplements> value ] : ( implementsInterface )* ;
    public final TamagoCDLParser.listimplements_return listimplements() throws RecognitionException {
        TamagoCDLParser.listimplements_return retval = new TamagoCDLParser.listimplements_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.implementsInterface_return implementsInterface24 =null;




        	retval.value = new ArrayList<TImplements>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:105:2: ( ( implementsInterface )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:105:4: ( implementsInterface )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:105:4: ( implementsInterface )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==49) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:105:5: implementsInterface
            	    {
            	    pushFollow(FOLLOW_implementsInterface_in_listimplements461);
            	    implementsInterface24=implementsInterface();

            	    state._fsp--;

            	    adaptor.addChild(root_0, implementsInterface24.getTree());

            	     retval.value.add((implementsInterface24!=null?implementsInterface24.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listimplements"


    public static class listrefine_return extends ParserRuleReturnScope {
        public Collection<TRefineService> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listrefine"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:108:1: listrefine returns [Collection<TRefineService> value ] : ( refineService )* ;
    public final TamagoCDLParser.listrefine_return listrefine() throws RecognitionException {
        TamagoCDLParser.listrefine_return retval = new TamagoCDLParser.listrefine_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.refineService_return refineService25 =null;




        	retval.value = new ArrayList<TRefineService>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:112:2: ( ( refineService )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:112:4: ( refineService )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:112:4: ( refineService )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==66) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:112:5: refineService
            	    {
            	    pushFollow(FOLLOW_refineService_in_listrefine489);
            	    refineService25=refineService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, refineService25.getTree());

            	     retval.value.add((refineService25!=null?refineService25.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listrefine"


    public static class listinclude_return extends ParserRuleReturnScope {
        public Collection<TIncludeService> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listinclude"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:114:1: listinclude returns [Collection<TIncludeService> value ] : ( includeService )* ;
    public final TamagoCDLParser.listinclude_return listinclude() throws RecognitionException {
        TamagoCDLParser.listinclude_return retval = new TamagoCDLParser.listinclude_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.includeService_return includeService26 =null;




        	retval.value = new ArrayList<TIncludeService>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:118:2: ( ( includeService )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:118:4: ( includeService )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:118:4: ( includeService )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==51) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:118:5: includeService
            	    {
            	    pushFollow(FOLLOW_includeService_in_listinclude515);
            	    includeService26=includeService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, includeService26.getTree());

            	     retval.value.add((includeService26!=null?includeService26.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listinclude"


    public static class listpercolators_return extends ParserRuleReturnScope {
        public Collection<TPercolator> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listpercolators"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:121:1: listpercolators returns [Collection<TPercolator> value] : (p= percolator )+ ;
    public final TamagoCDLParser.listpercolators_return listpercolators() throws RecognitionException {
        TamagoCDLParser.listpercolators_return retval = new TamagoCDLParser.listpercolators_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.percolator_return p =null;




        retval.value = new ArrayList<TPercolator>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:129:2: ( (p= percolator )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:129:4: (p= percolator )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:129:4: (p= percolator )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==58) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:129:5: p= percolator
            	    {
            	    pushFollow(FOLLOW_percolator_in_listpercolators549);
            	    p=percolator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, p.getTree());

            	     retval.value.add((p!=null?p.value:null)); 

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listpercolators"


    public static class listproperties_return extends ParserRuleReturnScope {
        public Collection<TProperty> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listproperties"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:131:1: listproperties returns [Collection<TProperty> value] : ( propertyDeclaration )+ ;
    public final TamagoCDLParser.listproperties_return listproperties() throws RecognitionException {
        TamagoCDLParser.listproperties_return retval = new TamagoCDLParser.listproperties_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.propertyDeclaration_return propertyDeclaration27 =null;




        retval.value = new ArrayList<TProperty>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:135:2: ( ( propertyDeclaration )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:135:4: ( propertyDeclaration )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:135:4: ( propertyDeclaration )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==61) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:135:5: propertyDeclaration
            	    {
            	    pushFollow(FOLLOW_propertyDeclaration_in_listproperties574);
            	    propertyDeclaration27=propertyDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, propertyDeclaration27.getTree());

            	     retval.value.add((propertyDeclaration27!=null?propertyDeclaration27.value:null)); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listproperties"


    public static class listprovides_return extends ParserRuleReturnScope {
        public Collection<TProvide> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listprovides"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:138:1: listprovides returns [Collection<TProvide> value] : ( provide )+ ;
    public final TamagoCDLParser.listprovides_return listprovides() throws RecognitionException {
        TamagoCDLParser.listprovides_return retval = new TamagoCDLParser.listprovides_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.provide_return provide28 =null;




         retval.value = new ArrayList<TProvide>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:142:2: ( ( provide )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:142:4: ( provide )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:142:4: ( provide )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==62) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:142:5: provide
            	    {
            	    pushFollow(FOLLOW_provide_in_listprovides600);
            	    provide28=provide();

            	    state._fsp--;

            	    adaptor.addChild(root_0, provide28.getTree());

            	     retval.value.add((provide28!=null?provide28.value:null)); 

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listprovides"


    public static class listrequires_return extends ParserRuleReturnScope {
        public Collection<TRequire> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listrequires"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:144:1: listrequires returns [Collection<TRequire> value] : ( require )+ ;
    public final TamagoCDLParser.listrequires_return listrequires() throws RecognitionException {
        TamagoCDLParser.listrequires_return retval = new TamagoCDLParser.listrequires_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.require_return require29 =null;




         retval.value = new ArrayList<TRequire>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:148:2: ( ( require )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:148:4: ( require )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:148:4: ( require )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==67) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:148:5: require
            	    {
            	    pushFollow(FOLLOW_require_in_listrequires624);
            	    require29=require();

            	    state._fsp--;

            	    adaptor.addChild(root_0, require29.getTree());

            	     retval.value.add((require29!=null?require29.value:null)); 

            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listrequires"


    public static class listinvariants_return extends ParserRuleReturnScope {
        public Collection<TInvariant> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listinvariants"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:150:1: listinvariants returns [ Collection<TInvariant> value] : ( invariantExpression )+ ;
    public final TamagoCDLParser.listinvariants_return listinvariants() throws RecognitionException {
        TamagoCDLParser.listinvariants_return retval = new TamagoCDLParser.listinvariants_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.invariantExpression_return invariantExpression30 =null;




        retval.value = new ArrayList<TInvariant>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:154:2: ( ( invariantExpression )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:154:4: ( invariantExpression )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:154:4: ( invariantExpression )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==53) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:154:5: invariantExpression
            	    {
            	    pushFollow(FOLLOW_invariantExpression_in_listinvariants648);
            	    invariantExpression30=invariantExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, invariantExpression30.getTree());

            	     retval.value.add((invariantExpression30!=null?invariantExpression30.value:null)) ; 

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listinvariants"


    public static class listmethods_return extends ParserRuleReturnScope {
        public Collection<TMethod> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listmethods"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:157:1: listmethods returns [ Collection<TMethod> value] : ( methodDeclaration )+ ;
    public final TamagoCDLParser.listmethods_return listmethods() throws RecognitionException {
        TamagoCDLParser.listmethods_return retval = new TamagoCDLParser.listmethods_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.methodDeclaration_return methodDeclaration31 =null;




        retval.value = new ArrayList<TMethod>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:161:2: ( ( methodDeclaration )+ )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:161:4: ( methodDeclaration )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:161:4: ( methodDeclaration )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==54) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:161:5: methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_listmethods674);
            	    methodDeclaration31=methodDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, methodDeclaration31.getTree());

            	     retval.value.add((methodDeclaration31!=null?methodDeclaration31.value:null)) ; 

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listmethods"


    public static class componentEntity_return extends ParserRuleReturnScope {
        public TComponent value;
        public TBehavior beh;
        public Collection<TPercolator> aperc;
        public Collection<TImplements> aimpl;
        public Collection<TRequire> areqs;
        public Collection<TProvide> aprov;
        public Collection<TProperty> aprop;
        public Collection<TInvariant> ainvs;
        public Collection<TMethod> ameth;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "componentEntity"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:165:1: componentEntity returns [TComponent value, TBehavior beh, Collection<TPercolator> aperc, Collection<TImplements> aimpl,\r\n\tCollection<TRequire> areqs, Collection<TProvide> aprov, Collection<TProperty> aprop, Collection<TInvariant> ainvs,\r\n\tCollection<TMethod> ameth ] : 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !;
    public final TamagoCDLParser.componentEntity_return componentEntity() throws RecognitionException {
        TamagoCDLParser.componentEntity_return retval = new TamagoCDLParser.componentEntity_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token label=null;
        Token string_literal32=null;
        Token char_literal33=null;
        Token char_literal34=null;
        TamagoCDLParser.percolator_return lperc =null;

        TamagoCDLParser.implementsInterface_return limpl =null;

        TamagoCDLParser.require_return lreq =null;

        TamagoCDLParser.provide_return lprov =null;

        TamagoCDLParser.propertyDeclaration_return lprop =null;

        TamagoCDLParser.invariantExpression_return linvs =null;

        TamagoCDLParser.methodDeclaration_return lmeth =null;

        TamagoCDLParser.behaviorDeclaration_return b =null;


        CommonTree label_tree=null;
        CommonTree string_literal32_tree=null;
        CommonTree char_literal33_tree=null;
        CommonTree char_literal34_tree=null;


         retval.aperc = new ArrayList<TPercolator>();
         retval.aimpl = new ArrayList<TImplements>();
         retval.areqs = new ArrayList<TRequire>();
         retval.aprov = new ArrayList<TProvide>();
         retval.aprop = new ArrayList<TProperty>();
         retval.ainvs = new ArrayList<TInvariant>();
         retval.ameth = new ArrayList<TMethod>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:177:12: ( 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:178:12: 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal32=(Token)match(input,42,FOLLOW_42_in_componentEntity701); 
            string_literal32_tree = 
            (CommonTree)adaptor.create(string_literal32)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal32_tree, root_0);


            label=(Token)match(input,ID,FOLLOW_ID_in_componentEntity706); 
            label_tree = 
            (CommonTree)adaptor.create(label)
            ;
            adaptor.addChild(root_0, label_tree);


            char_literal33=(Token)match(input,83,FOLLOW_83_in_componentEntity708); 

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:179:6: (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )*
            loop17:
            do {
                int alt17=8;
                switch ( input.LA(1) ) {
                case 58:
                    {
                    alt17=1;
                    }
                    break;
                case 49:
                    {
                    alt17=2;
                    }
                    break;
                case 67:
                    {
                    alt17=3;
                    }
                    break;
                case 62:
                    {
                    alt17=4;
                    }
                    break;
                case 61:
                    {
                    alt17=5;
                    }
                    break;
                case 53:
                    {
                    alt17=6;
                    }
                    break;
                case 54:
                    {
                    alt17=7;
                    }
                    break;

                }

                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:180:6: lperc= percolator
            	    {
            	    pushFollow(FOLLOW_percolator_in_componentEntity715);
            	    lperc=percolator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lperc.getTree());

            	     retval.aperc.add((lperc!=null?lperc.value:null)); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:181:6: limpl= implementsInterface
            	    {
            	    pushFollow(FOLLOW_implementsInterface_in_componentEntity723);
            	    limpl=implementsInterface();

            	    state._fsp--;

            	    adaptor.addChild(root_0, limpl.getTree());

            	     retval.aimpl.add((limpl!=null?limpl.value:null)); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:182:5: lreq= require
            	    {
            	    pushFollow(FOLLOW_require_in_componentEntity731);
            	    lreq=require();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lreq.getTree());

            	     retval.areqs.add((lreq!=null?lreq.value:null)); 

            	    }
            	    break;
            	case 4 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:183:6: lprov= provide
            	    {
            	    pushFollow(FOLLOW_provide_in_componentEntity739);
            	    lprov=provide();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lprov.getTree());

            	     retval.aprov.add((lprov!=null?lprov.value:null)); 

            	    }
            	    break;
            	case 5 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:184:6: lprop= propertyDeclaration
            	    {
            	    pushFollow(FOLLOW_propertyDeclaration_in_componentEntity747);
            	    lprop=propertyDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lprop.getTree());

            	     retval.aprop.add((lprop!=null?lprop.value:null)); 

            	    }
            	    break;
            	case 6 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:185:6: linvs= invariantExpression
            	    {
            	    pushFollow(FOLLOW_invariantExpression_in_componentEntity755);
            	    linvs=invariantExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, linvs.getTree());

            	     retval.ainvs.add((linvs!=null?linvs.value:null)); 

            	    }
            	    break;
            	case 7 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:186:6: lmeth= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_componentEntity763);
            	    lmeth=methodDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lmeth.getTree());

            	     retval.ameth.add((lmeth!=null?lmeth.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:187:2: (b= behaviorDeclaration )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==38) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:187:2: b= behaviorDeclaration
                    {
                    pushFollow(FOLLOW_behaviorDeclaration_in_componentEntity772);
                    b=behaviorDeclaration();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.beh =(b!=null?b.value:null); 

                    }
                    break;

            }


            char_literal34=(Token)match(input,85,FOLLOW_85_in_componentEntity778); 

             retval.value = TamagoCDLEaseFactory.component(retval.value,(label!=null?label.getText():null),retval.aimpl,retval.aprov,retval.areqs,retval.aprop,retval.ainvs,retval.ameth,retval.beh,retval.aperc);

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "componentEntity"


    public static class implementsInterface_return extends ParserRuleReturnScope {
        public TImplements value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "implementsInterface"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:192:1: implementsInterface returns [TImplements value] : 'implements' ^p= qualifiedName ';' !;
    public final TamagoCDLParser.implementsInterface_return implementsInterface() throws RecognitionException {
        TamagoCDLParser.implementsInterface_return retval = new TamagoCDLParser.implementsInterface_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal35=null;
        Token char_literal36=null;
        TamagoCDLParser.qualifiedName_return p =null;


        CommonTree string_literal35_tree=null;
        CommonTree char_literal36_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:193:2: ( 'implements' ^p= qualifiedName ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:193:4: 'implements' ^p= qualifiedName ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal35=(Token)match(input,49,FOLLOW_49_in_implementsInterface796); 
            string_literal35_tree = 
            (CommonTree)adaptor.create(string_literal35)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal35_tree, root_0);


            pushFollow(FOLLOW_qualifiedName_in_implementsInterface801);
            p=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());

            char_literal36=(Token)match(input,29,FOLLOW_29_in_implementsInterface803); 

             retval.value = new TIImplements((p!=null?p.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "implementsInterface"


    public static class refineService_return extends ParserRuleReturnScope {
        public TRefineService value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "refineService"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:196:1: refineService returns [TRefineService value] : ( 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'refine' ^ 'service' ! qualifiedName ';' !);
    public final TamagoCDLParser.refineService_return refineService() throws RecognitionException {
        TamagoCDLParser.refineService_return retval = new TamagoCDLParser.refineService_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal37=null;
        Token string_literal38=null;
        Token string_literal39=null;
        Token char_literal40=null;
        Token string_literal41=null;
        Token string_literal42=null;
        Token char_literal44=null;
        TamagoCDLParser.qualifiedName_return q =null;

        TamagoCDLParser.qualifiedName_return qualifiedName43 =null;


        CommonTree n_tree=null;
        CommonTree string_literal37_tree=null;
        CommonTree string_literal38_tree=null;
        CommonTree string_literal39_tree=null;
        CommonTree char_literal40_tree=null;
        CommonTree string_literal41_tree=null;
        CommonTree string_literal42_tree=null;
        CommonTree char_literal44_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:197:2: ( 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'refine' ^ 'service' ! qualifiedName ';' !)
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==66) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==68) ) {
                    int LA19_2 = input.LA(3);

                    if ( (LA19_2==ID) ) {
                        int LA19_3 = input.LA(4);

                        if ( (LA19_3==50) ) {
                            alt19=1;
                        }
                        else if ( (LA19_3==26||LA19_3==29) ) {
                            alt19=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 19, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:197:4: 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal37=(Token)match(input,66,FOLLOW_66_in_refineService821); 
                    string_literal37_tree = 
                    (CommonTree)adaptor.create(string_literal37)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal37_tree, root_0);


                    string_literal38=(Token)match(input,68,FOLLOW_68_in_refineService824); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_refineService829); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    string_literal39=(Token)match(input,50,FOLLOW_50_in_refineService831); 

                    pushFollow(FOLLOW_qualifiedName_in_refineService836);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    char_literal40=(Token)match(input,29,FOLLOW_29_in_refineService838); 

                     retval.value = TamagoCDLEaseFactory.refine((n!=null?n.getText():null),(q!=null?q.value:null));  

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:198:10: 'refine' ^ 'service' ! qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal41=(Token)match(input,66,FOLLOW_66_in_refineService852); 
                    string_literal41_tree = 
                    (CommonTree)adaptor.create(string_literal41)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal41_tree, root_0);


                    string_literal42=(Token)match(input,68,FOLLOW_68_in_refineService855); 

                    pushFollow(FOLLOW_qualifiedName_in_refineService858);
                    qualifiedName43=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedName43.getTree());

                    char_literal44=(Token)match(input,29,FOLLOW_29_in_refineService860); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "refineService"


    public static class includeService_return extends ParserRuleReturnScope {
        public TIncludeService value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "includeService"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:201:1: includeService returns [TIncludeService value] : ( 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'include' ^ 'service' ! qualifiedName ';' !);
    public final TamagoCDLParser.includeService_return includeService() throws RecognitionException {
        TamagoCDLParser.includeService_return retval = new TamagoCDLParser.includeService_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal45=null;
        Token string_literal46=null;
        Token string_literal47=null;
        Token char_literal48=null;
        Token string_literal49=null;
        Token string_literal50=null;
        Token char_literal52=null;
        TamagoCDLParser.qualifiedName_return q =null;

        TamagoCDLParser.qualifiedName_return qualifiedName51 =null;


        CommonTree n_tree=null;
        CommonTree string_literal45_tree=null;
        CommonTree string_literal46_tree=null;
        CommonTree string_literal47_tree=null;
        CommonTree char_literal48_tree=null;
        CommonTree string_literal49_tree=null;
        CommonTree string_literal50_tree=null;
        CommonTree char_literal52_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:202:2: ( 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'include' ^ 'service' ! qualifiedName ';' !)
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==51) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==68) ) {
                    int LA20_2 = input.LA(3);

                    if ( (LA20_2==ID) ) {
                        int LA20_3 = input.LA(4);

                        if ( (LA20_3==50) ) {
                            alt20=1;
                        }
                        else if ( (LA20_3==26||LA20_3==29) ) {
                            alt20=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 20, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:202:4: 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal45=(Token)match(input,51,FOLLOW_51_in_includeService877); 
                    string_literal45_tree = 
                    (CommonTree)adaptor.create(string_literal45)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal45_tree, root_0);


                    string_literal46=(Token)match(input,68,FOLLOW_68_in_includeService880); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_includeService885); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    string_literal47=(Token)match(input,50,FOLLOW_50_in_includeService887); 

                    pushFollow(FOLLOW_qualifiedName_in_includeService892);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    char_literal48=(Token)match(input,29,FOLLOW_29_in_includeService894); 

                     retval.value = TamagoCDLEaseFactory.include((n!=null?n.getText():null),(q!=null?q.value:null)); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:203:10: 'include' ^ 'service' ! qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal49=(Token)match(input,51,FOLLOW_51_in_includeService908); 
                    string_literal49_tree = 
                    (CommonTree)adaptor.create(string_literal49)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal49_tree, root_0);


                    string_literal50=(Token)match(input,68,FOLLOW_68_in_includeService911); 

                    pushFollow(FOLLOW_qualifiedName_in_includeService914);
                    qualifiedName51=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedName51.getTree());

                    char_literal52=(Token)match(input,29,FOLLOW_29_in_includeService916); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "includeService"


    public static class propertyDeclaration_return extends ParserRuleReturnScope {
        public TProperty value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "propertyDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:206:1: propertyDeclaration returns [TProperty value ] : 'property' ^a= accessProperty t= type n= ID ';' !;
    public final TamagoCDLParser.propertyDeclaration_return propertyDeclaration() throws RecognitionException {
        TamagoCDLParser.propertyDeclaration_return retval = new TamagoCDLParser.propertyDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal53=null;
        Token char_literal54=null;
        TamagoCDLParser.accessProperty_return a =null;

        TamagoCDLParser.type_return t =null;


        CommonTree n_tree=null;
        CommonTree string_literal53_tree=null;
        CommonTree char_literal54_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:207:2: ( 'property' ^a= accessProperty t= type n= ID ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:207:4: 'property' ^a= accessProperty t= type n= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal53=(Token)match(input,61,FOLLOW_61_in_propertyDeclaration933); 
            string_literal53_tree = 
            (CommonTree)adaptor.create(string_literal53)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal53_tree, root_0);


            pushFollow(FOLLOW_accessProperty_in_propertyDeclaration938);
            a=accessProperty();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            pushFollow(FOLLOW_type_in_propertyDeclaration942);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration946); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            char_literal54=(Token)match(input,29,FOLLOW_29_in_propertyDeclaration948); 

             
            			retval.value = new TIProperty((n!=null?n.getText():null),(t!=null?t.value:null),(a!=null?a.value:null)); 
            		

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "propertyDeclaration"


    public static class accessProperty_return extends ParserRuleReturnScope {
        public TAccess value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "accessProperty"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:213:1: accessProperty returns [TAccess value] : ( 'readwrite' | 'read' | 'write' );
    public final TamagoCDLParser.accessProperty_return accessProperty() throws RecognitionException {
        TamagoCDLParser.accessProperty_return retval = new TamagoCDLParser.accessProperty_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal55=null;
        Token string_literal56=null;
        Token string_literal57=null;

        CommonTree string_literal55_tree=null;
        CommonTree string_literal56_tree=null;
        CommonTree string_literal57_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:214:2: ( 'readwrite' | 'read' | 'write' )
            int alt21=3;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt21=1;
                }
                break;
            case 63:
                {
                alt21=2;
                }
                break;
            case 82:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:215:4: 'readwrite'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal55=(Token)match(input,64,FOLLOW_64_in_accessProperty974); 
                    string_literal55_tree = 
                    (CommonTree)adaptor.create(string_literal55)
                    ;
                    adaptor.addChild(root_0, string_literal55_tree);


                     retval.value = new TIAccess("rw");  

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:216:4: 'read'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal56=(Token)match(input,63,FOLLOW_63_in_accessProperty981); 
                    string_literal56_tree = 
                    (CommonTree)adaptor.create(string_literal56)
                    ;
                    adaptor.addChild(root_0, string_literal56_tree);


                     retval.value = new TIAccess("r"); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:217:4: 'write'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal57=(Token)match(input,82,FOLLOW_82_in_accessProperty993); 
                    string_literal57_tree = 
                    (CommonTree)adaptor.create(string_literal57)
                    ;
                    adaptor.addChild(root_0, string_literal57_tree);


                     retval.value = new TIAccess("w"); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "accessProperty"


    public static class type_return extends ParserRuleReturnScope {
        public TType value;
        public String prim;
        public boolean flags;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:220:1: type returns [ TType value, String prim, boolean flags] : (p= primitiveType |q= qualifiedName ) ( '[]' )? ;
    public final TamagoCDLParser.type_return type() throws RecognitionException {
        TamagoCDLParser.type_return retval = new TamagoCDLParser.type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal58=null;
        TamagoCDLParser.primitiveType_return p =null;

        TamagoCDLParser.qualifiedName_return q =null;


        CommonTree string_literal58_tree=null;


        	retval.flags =false;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:231:2: ( (p= primitiveType |q= qualifiedName ) ( '[]' )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:231:4: (p= primitiveType |q= qualifiedName ) ( '[]' )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:231:4: (p= primitiveType |q= qualifiedName )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==32||(LA22_0 >= 39 && LA22_0 <= 40)||LA22_0==44||LA22_0==52||LA22_0==65||LA22_0==72||LA22_0==79) ) {
                alt22=1;
            }
            else if ( (LA22_0==ID) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:231:5: p= primitiveType
                    {
                    pushFollow(FOLLOW_primitiveType_in_type1028);
                    p=primitiveType();

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    retval.prim =(p!=null?p.value:null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:231:41: q= qualifiedName
                    {
                    pushFollow(FOLLOW_qualifiedName_in_type1035);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    retval.prim =(q!=null?q.value:null);

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:232:3: ( '[]' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==34) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:232:4: '[]'
                    {
                    string_literal58=(Token)match(input,34,FOLLOW_34_in_type1044); 
                    string_literal58_tree = 
                    (CommonTree)adaptor.create(string_literal58)
                    ;
                    adaptor.addChild(root_0, string_literal58_tree);


                     retval.flags = true; 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            	if(retval.flags)
            		retval.value =TIType.generateType(retval.prim+"[]");
            	else
            		retval.value =TIType.generateType(retval.prim);
            	

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "type"


    public static class primitiveType_return extends ParserRuleReturnScope {
        public String value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primitiveType"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:235:1: primitiveType returns [String value ] : ( 'int' | 'void' | 'bool' | 'boolean' | 'real' | 'double' | 'String' | 'string' );
    public final TamagoCDLParser.primitiveType_return primitiveType() throws RecognitionException {
        TamagoCDLParser.primitiveType_return retval = new TamagoCDLParser.primitiveType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal59=null;
        Token string_literal60=null;
        Token string_literal61=null;
        Token string_literal62=null;
        Token string_literal63=null;
        Token string_literal64=null;
        Token string_literal65=null;
        Token string_literal66=null;

        CommonTree string_literal59_tree=null;
        CommonTree string_literal60_tree=null;
        CommonTree string_literal61_tree=null;
        CommonTree string_literal62_tree=null;
        CommonTree string_literal63_tree=null;
        CommonTree string_literal64_tree=null;
        CommonTree string_literal65_tree=null;
        CommonTree string_literal66_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:236:2: ( 'int' | 'void' | 'bool' | 'boolean' | 'real' | 'double' | 'String' | 'string' )
            int alt24=8;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt24=1;
                }
                break;
            case 79:
                {
                alt24=2;
                }
                break;
            case 39:
                {
                alt24=3;
                }
                break;
            case 40:
                {
                alt24=4;
                }
                break;
            case 65:
                {
                alt24=5;
                }
                break;
            case 44:
                {
                alt24=6;
                }
                break;
            case 32:
                {
                alt24=7;
                }
                break;
            case 72:
                {
                alt24=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:236:4: 'int'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal59=(Token)match(input,52,FOLLOW_52_in_primitiveType1068); 
                    string_literal59_tree = 
                    (CommonTree)adaptor.create(string_literal59)
                    ;
                    adaptor.addChild(root_0, string_literal59_tree);


                     retval.value ="int"; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:237:4: 'void'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal60=(Token)match(input,79,FOLLOW_79_in_primitiveType1076); 
                    string_literal60_tree = 
                    (CommonTree)adaptor.create(string_literal60)
                    ;
                    adaptor.addChild(root_0, string_literal60_tree);


                     retval.value ="void"; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:238:4: 'bool'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal61=(Token)match(input,39,FOLLOW_39_in_primitiveType1085); 
                    string_literal61_tree = 
                    (CommonTree)adaptor.create(string_literal61)
                    ;
                    adaptor.addChild(root_0, string_literal61_tree);


                     retval.value ="bool"; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:239:4: 'boolean'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal62=(Token)match(input,40,FOLLOW_40_in_primitiveType1094); 
                    string_literal62_tree = 
                    (CommonTree)adaptor.create(string_literal62)
                    ;
                    adaptor.addChild(root_0, string_literal62_tree);


                     retval.value ="bool"; 

                    }
                    break;
                case 5 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:240:4: 'real'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal63=(Token)match(input,65,FOLLOW_65_in_primitiveType1103); 
                    string_literal63_tree = 
                    (CommonTree)adaptor.create(string_literal63)
                    ;
                    adaptor.addChild(root_0, string_literal63_tree);


                     retval.value ="real"; 

                    }
                    break;
                case 6 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:241:4: 'double'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal64=(Token)match(input,44,FOLLOW_44_in_primitiveType1112); 
                    string_literal64_tree = 
                    (CommonTree)adaptor.create(string_literal64)
                    ;
                    adaptor.addChild(root_0, string_literal64_tree);


                     retval.value ="real"; 

                    }
                    break;
                case 7 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:242:4: 'String'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal65=(Token)match(input,32,FOLLOW_32_in_primitiveType1121); 
                    string_literal65_tree = 
                    (CommonTree)adaptor.create(string_literal65)
                    ;
                    adaptor.addChild(root_0, string_literal65_tree);


                     retval.value ="String"; 

                    }
                    break;
                case 8 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:243:4: 'string'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal66=(Token)match(input,72,FOLLOW_72_in_primitiveType1130); 
                    string_literal66_tree = 
                    (CommonTree)adaptor.create(string_literal66)
                    ;
                    adaptor.addChild(root_0, string_literal66_tree);


                     retval.value ="String"; 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "primitiveType"


    public static class methodDeclaration_return extends ParserRuleReturnScope {
        public TMethod value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:246:1: methodDeclaration returns [TMethod value] : 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !;
    public final TamagoCDLParser.methodDeclaration_return methodDeclaration() throws RecognitionException {
        TamagoCDLParser.methodDeclaration_return retval = new TamagoCDLParser.methodDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token d=null;
        Token string_literal67=null;
        Token char_literal68=null;
        Token string_literal69=null;
        Token char_literal70=null;
        Token char_literal71=null;
        TamagoCDLParser.type_return t =null;

        TamagoCDLParser.parameters_return a =null;

        TamagoCDLParser.preconditionExpression_return p =null;

        TamagoCDLParser.postconditionExpression_return q =null;


        CommonTree n_tree=null;
        CommonTree d_tree=null;
        CommonTree string_literal67_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree string_literal69_tree=null;
        CommonTree char_literal70_tree=null;
        CommonTree char_literal71_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:247:2: ( 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:248:3: 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal67=(Token)match(input,54,FOLLOW_54_in_methodDeclaration1151); 
            string_literal67_tree = 
            (CommonTree)adaptor.create(string_literal67)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal67_tree, root_0);


            pushFollow(FOLLOW_type_in_methodDeclaration1156);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration1160); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            pushFollow(FOLLOW_parameters_in_methodDeclaration1164);
            a=parameters();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            char_literal68=(Token)match(input,83,FOLLOW_83_in_methodDeclaration1166); 

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:249:4: ( 'id' ^d= ID ';' !)?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==48) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:249:5: 'id' ^d= ID ';' !
                    {
                    string_literal69=(Token)match(input,48,FOLLOW_48_in_methodDeclaration1173); 
                    string_literal69_tree = 
                    (CommonTree)adaptor.create(string_literal69)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal69_tree, root_0);


                    d=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration1178); 
                    d_tree = 
                    (CommonTree)adaptor.create(d)
                    ;
                    adaptor.addChild(root_0, d_tree);


                    char_literal70=(Token)match(input,29,FOLLOW_29_in_methodDeclaration1180); 

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:250:4: (p= preconditionExpression )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==60) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:250:5: p= preconditionExpression
                    {
                    pushFollow(FOLLOW_preconditionExpression_in_methodDeclaration1191);
                    p=preconditionExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:251:4: (q= postconditionExpression )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==59) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:251:5: q= postconditionExpression
                    {
                    pushFollow(FOLLOW_postconditionExpression_in_methodDeclaration1201);
                    q=postconditionExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    }
                    break;

            }


            char_literal71=(Token)match(input,85,FOLLOW_85_in_methodDeclaration1207); 

            retval.value = TamagoCDLEaseFactory.method((t!=null?t.value:null), (n!=null?n.getText():null), (a!=null?a.params:null), (d!=null?d.getText():null), (p!=null?p.value:null), (q!=null?q.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "methodDeclaration"


    public static class parameters_return extends ParserRuleReturnScope {
        public Collection<TParameter> params;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameters"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:256:1: parameters returns [Collection<TParameter> params] : '(' (a= type b= ID ( ',' c= type d= ID )* )? ')' ;
    public final TamagoCDLParser.parameters_return parameters() throws RecognitionException {
        TamagoCDLParser.parameters_return retval = new TamagoCDLParser.parameters_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token b=null;
        Token d=null;
        Token char_literal72=null;
        Token char_literal73=null;
        Token char_literal74=null;
        TamagoCDLParser.type_return a =null;

        TamagoCDLParser.type_return c =null;


        CommonTree b_tree=null;
        CommonTree d_tree=null;
        CommonTree char_literal72_tree=null;
        CommonTree char_literal73_tree=null;
        CommonTree char_literal74_tree=null;


        retval.params = new ArrayList<TParameter>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:2: ( '(' (a= type b= ID ( ',' c= type d= ID )* )? ')' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:4: '(' (a= type b= ID ( ',' c= type d= ID )* )? ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal72=(Token)match(input,22,FOLLOW_22_in_parameters1230); 
            char_literal72_tree = 
            (CommonTree)adaptor.create(char_literal72)
            ;
            adaptor.addChild(root_0, char_literal72_tree);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:8: (a= type b= ID ( ',' c= type d= ID )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==ID||LA29_0==32||(LA29_0 >= 39 && LA29_0 <= 40)||LA29_0==44||LA29_0==52||LA29_0==65||LA29_0==72||LA29_0==79) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:9: a= type b= ID ( ',' c= type d= ID )*
                    {
                    pushFollow(FOLLOW_type_in_parameters1235);
                    a=type();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    b=(Token)match(input,ID,FOLLOW_ID_in_parameters1239); 
                    b_tree = 
                    (CommonTree)adaptor.create(b)
                    ;
                    adaptor.addChild(root_0, b_tree);


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:21: ( ',' c= type d= ID )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==25) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:260:22: ',' c= type d= ID
                    	    {
                    	    char_literal73=(Token)match(input,25,FOLLOW_25_in_parameters1242); 
                    	    char_literal73_tree = 
                    	    (CommonTree)adaptor.create(char_literal73)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal73_tree);


                    	    pushFollow(FOLLOW_type_in_parameters1246);
                    	    c=type();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, c.getTree());

                    	    d=(Token)match(input,ID,FOLLOW_ID_in_parameters1250); 
                    	    d_tree = 
                    	    (CommonTree)adaptor.create(d)
                    	    ;
                    	    adaptor.addChild(root_0, d_tree);


                    	     retval.params.add(TamagoCDLEaseFactory.param((c!=null?c.value:null),(d!=null?d.getText():null))); 

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                     retval.params.add(TamagoCDLEaseFactory.param((a!=null?a.value:null),(b!=null?b.getText():null))); 

                    }
                    break;

            }


            char_literal74=(Token)match(input,23,FOLLOW_23_in_parameters1281); 
            char_literal74_tree = 
            (CommonTree)adaptor.create(char_literal74)
            ;
            adaptor.addChild(root_0, char_literal74_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameters"


    public static class arguments_return extends ParserRuleReturnScope {
        public Collection<TExpression> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arguments"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:268:1: arguments returns [Collection<TExpression> value] : '(' (a= expression ( ',' b= expression )* )? ')' ;
    public final TamagoCDLParser.arguments_return arguments() throws RecognitionException {
        TamagoCDLParser.arguments_return retval = new TamagoCDLParser.arguments_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal75=null;
        Token char_literal76=null;
        Token char_literal77=null;
        TamagoCDLParser.expression_return a =null;

        TamagoCDLParser.expression_return b =null;


        CommonTree char_literal75_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree char_literal77_tree=null;


        retval.value = new ArrayList<TExpression>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:272:2: ( '(' (a= expression ( ',' b= expression )* )? ')' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:272:4: '(' (a= expression ( ',' b= expression )* )? ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal75=(Token)match(input,22,FOLLOW_22_in_arguments1303); 
            char_literal75_tree = 
            (CommonTree)adaptor.create(char_literal75)
            ;
            adaptor.addChild(root_0, char_literal75_tree);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:272:8: (a= expression ( ',' b= expression )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==FLOAT||(LA31_0 >= ID && LA31_0 <= INT)||LA31_0==NOTOPERATOR||LA31_0==QUANTIFIER||LA31_0==STRING||LA31_0==20||LA31_0==22||LA31_0==31||LA31_0==46||(LA31_0 >= 56 && LA31_0 <= 57)||LA31_0==73||LA31_0==77) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:272:9: a= expression ( ',' b= expression )*
                    {
                    pushFollow(FOLLOW_expression_in_arguments1308);
                    a=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    retval.value.add((a!=null?a.value:null));

                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:273:4: ( ',' b= expression )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==25) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:273:5: ',' b= expression
                    	    {
                    	    char_literal76=(Token)match(input,25,FOLLOW_25_in_arguments1316); 
                    	    char_literal76_tree = 
                    	    (CommonTree)adaptor.create(char_literal76)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal76_tree);


                    	    pushFollow(FOLLOW_expression_in_arguments1320);
                    	    b=expression();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, b.getTree());

                    	     retval.value.add((b!=null?b.value:null)); 

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal77=(Token)match(input,23,FOLLOW_23_in_arguments1328); 
            char_literal77_tree = 
            (CommonTree)adaptor.create(char_literal77)
            ;
            adaptor.addChild(root_0, char_literal77_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arguments"


    public static class behaviorDeclaration_return extends ParserRuleReturnScope {
        public TIBehavior value;
        public String def;
        public Collection<TState> states;
        public Collection<TTransition> transitions;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "behaviorDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:276:1: behaviorDeclaration returns [TIBehavior value, String def, Collection<TState> states, Collection<TTransition> transitions ] : 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}' ;
    public final TamagoCDLParser.behaviorDeclaration_return behaviorDeclaration() throws RecognitionException {
        TamagoCDLParser.behaviorDeclaration_return retval = new TamagoCDLParser.behaviorDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal78=null;
        Token char_literal79=null;
        Token string_literal80=null;
        Token string_literal81=null;
        Token char_literal82=null;
        Token string_literal83=null;
        Token char_literal84=null;
        Token char_literal85=null;
        Token string_literal86=null;
        Token char_literal87=null;
        Token char_literal88=null;
        Token char_literal89=null;
        TamagoCDLParser.stateDeclaration_return s =null;

        TamagoCDLParser.transitionDeclaration_return t =null;


        CommonTree n_tree=null;
        CommonTree string_literal78_tree=null;
        CommonTree char_literal79_tree=null;
        CommonTree string_literal80_tree=null;
        CommonTree string_literal81_tree=null;
        CommonTree char_literal82_tree=null;
        CommonTree string_literal83_tree=null;
        CommonTree char_literal84_tree=null;
        CommonTree char_literal85_tree=null;
        CommonTree string_literal86_tree=null;
        CommonTree char_literal87_tree=null;
        CommonTree char_literal88_tree=null;
        CommonTree char_literal89_tree=null;


        	retval.states = new ArrayList<TState>();
        	retval.transitions = new ArrayList<TTransition>();
        	retval.def = "";

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:283:2: ( 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:283:4: 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal78=(Token)match(input,38,FOLLOW_38_in_behaviorDeclaration1349); 
            string_literal78_tree = 
            (CommonTree)adaptor.create(string_literal78)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal78_tree, root_0);


            char_literal79=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1352); 

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:284:2: ( 'default' ^ 'state' !n= ID ';' !)?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==43) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:284:3: 'default' ^ 'state' !n= ID ';' !
                    {
                    string_literal80=(Token)match(input,43,FOLLOW_43_in_behaviorDeclaration1357); 
                    string_literal80_tree = 
                    (CommonTree)adaptor.create(string_literal80)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal80_tree, root_0);


                    string_literal81=(Token)match(input,70,FOLLOW_70_in_behaviorDeclaration1360); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_behaviorDeclaration1365); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal82=(Token)match(input,29,FOLLOW_29_in_behaviorDeclaration1367); 

                    retval.def = (n!=null?n.getText():null);

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:285:2: ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==71) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:285:3: 'states' ^ '{' ! (s= stateDeclaration )* '}' !
                    {
                    string_literal83=(Token)match(input,71,FOLLOW_71_in_behaviorDeclaration1376); 
                    string_literal83_tree = 
                    (CommonTree)adaptor.create(string_literal83)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal83_tree, root_0);


                    char_literal84=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1379); 

                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:286:3: (s= stateDeclaration )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==70) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:286:4: s= stateDeclaration
                    	    {
                    	    pushFollow(FOLLOW_stateDeclaration_in_behaviorDeclaration1387);
                    	    s=stateDeclaration();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, s.getTree());

                    	    retval.states.add((s!=null?s.value:null)); 

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);


                    char_literal85=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1395); 

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:288:2: ( 'transitions' '{' (t= transitionDeclaration )* '}' )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==76) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:288:3: 'transitions' '{' (t= transitionDeclaration )* '}'
                    {
                    string_literal86=(Token)match(input,76,FOLLOW_76_in_behaviorDeclaration1402); 
                    string_literal86_tree = 
                    (CommonTree)adaptor.create(string_literal86)
                    ;
                    adaptor.addChild(root_0, string_literal86_tree);


                    char_literal87=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1404); 
                    char_literal87_tree = 
                    (CommonTree)adaptor.create(char_literal87)
                    ;
                    adaptor.addChild(root_0, char_literal87_tree);


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:289:3: (t= transitionDeclaration )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==75) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:289:4: t= transitionDeclaration
                    	    {
                    	    pushFollow(FOLLOW_transitionDeclaration_in_behaviorDeclaration1411);
                    	    t=transitionDeclaration();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, t.getTree());

                    	    retval.transitions.add((t!=null?t.value:null));

                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);


                    char_literal88=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1418); 
                    char_literal88_tree = 
                    (CommonTree)adaptor.create(char_literal88)
                    ;
                    adaptor.addChild(root_0, char_literal88_tree);


                    }
                    break;

            }


            char_literal89=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1423); 
            char_literal89_tree = 
            (CommonTree)adaptor.create(char_literal89)
            ;
            adaptor.addChild(root_0, char_literal89_tree);


             retval.value = new TIBehavior(retval.states,retval.transitions, retval.def); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "behaviorDeclaration"


    public static class stateDeclaration_return extends ParserRuleReturnScope {
        public Collection<TAllow> allows;
        public TState value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:294:1: stateDeclaration returns [ Collection<TAllow> allows, TState value] : 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !;
    public final TamagoCDLParser.stateDeclaration_return stateDeclaration() throws RecognitionException {
        TamagoCDLParser.stateDeclaration_return retval = new TamagoCDLParser.stateDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token i=null;
        Token string_literal90=null;
        Token char_literal91=null;
        Token char_literal92=null;
        TamagoCDLParser.allowDeclaration_return a =null;


        CommonTree i_tree=null;
        CommonTree string_literal90_tree=null;
        CommonTree char_literal91_tree=null;
        CommonTree char_literal92_tree=null;


        retval.allows = new ArrayList<TAllow>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:299:2: ( 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:299:4: 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal90=(Token)match(input,70,FOLLOW_70_in_stateDeclaration1447); 
            string_literal90_tree = 
            (CommonTree)adaptor.create(string_literal90)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal90_tree, root_0);


            i=(Token)match(input,ID,FOLLOW_ID_in_stateDeclaration1452); 
            i_tree = 
            (CommonTree)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            char_literal91=(Token)match(input,83,FOLLOW_83_in_stateDeclaration1454); 

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:299:23: (a= allowDeclaration )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==36) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:299:24: a= allowDeclaration
            	    {
            	    pushFollow(FOLLOW_allowDeclaration_in_stateDeclaration1460);
            	    a=allowDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, a.getTree());

            	     retval.allows.add((a!=null?a.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            char_literal92=(Token)match(input,85,FOLLOW_85_in_stateDeclaration1466); 

              retval.value = new TIState((i!=null?i.getText():null), retval.allows);  

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateDeclaration"


    public static class allowDeclaration_return extends ParserRuleReturnScope {
        public TAllow value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "allowDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:303:1: allowDeclaration returns [TAllow value] : 'allow' ^n= ID ';' !;
    public final TamagoCDLParser.allowDeclaration_return allowDeclaration() throws RecognitionException {
        TamagoCDLParser.allowDeclaration_return retval = new TamagoCDLParser.allowDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token string_literal93=null;
        Token char_literal94=null;

        CommonTree n_tree=null;
        CommonTree string_literal93_tree=null;
        CommonTree char_literal94_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:304:2: ( 'allow' ^n= ID ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:304:4: 'allow' ^n= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal93=(Token)match(input,36,FOLLOW_36_in_allowDeclaration1487); 
            string_literal93_tree = 
            (CommonTree)adaptor.create(string_literal93)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal93_tree, root_0);


            n=(Token)match(input,ID,FOLLOW_ID_in_allowDeclaration1492); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            char_literal94=(Token)match(input,29,FOLLOW_29_in_allowDeclaration1494); 

            retval.value = new TIAllow((n!=null?n.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "allowDeclaration"


    public static class transitionDeclaration_return extends ParserRuleReturnScope {
        public TTransition value;
        public TExpression guard;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "transitionDeclaration"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:307:1: transitionDeclaration returns [TTransition value, TExpression guard] : 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !;
    public final TamagoCDLParser.transitionDeclaration_return transitionDeclaration() throws RecognitionException {
        TamagoCDLParser.transitionDeclaration_return retval = new TamagoCDLParser.transitionDeclaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token f=null;
        Token t=null;
        Token w=null;
        Token string_literal95=null;
        Token string_literal96=null;
        Token string_literal97=null;
        Token string_literal98=null;
        Token string_literal99=null;
        Token char_literal100=null;
        TamagoCDLParser.expression_return e =null;


        CommonTree f_tree=null;
        CommonTree t_tree=null;
        CommonTree w_tree=null;
        CommonTree string_literal95_tree=null;
        CommonTree string_literal96_tree=null;
        CommonTree string_literal97_tree=null;
        CommonTree string_literal98_tree=null;
        CommonTree string_literal99_tree=null;
        CommonTree char_literal100_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:309:2: ( 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !)
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:309:4: 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal95=(Token)match(input,75,FOLLOW_75_in_transitionDeclaration1513); 
            string_literal95_tree = 
            (CommonTree)adaptor.create(string_literal95)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal95_tree, root_0);


            string_literal96=(Token)match(input,47,FOLLOW_47_in_transitionDeclaration1516); 

            f=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1521); 
            f_tree = 
            (CommonTree)adaptor.create(f)
            ;
            adaptor.addChild(root_0, f_tree);


            string_literal97=(Token)match(input,74,FOLLOW_74_in_transitionDeclaration1523); 

            t=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1528); 
            t_tree = 
            (CommonTree)adaptor.create(t)
            ;
            adaptor.addChild(root_0, t_tree);


            string_literal98=(Token)match(input,81,FOLLOW_81_in_transitionDeclaration1530); 

            w=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1535); 
            w_tree = 
            (CommonTree)adaptor.create(w)
            ;
            adaptor.addChild(root_0, w_tree);


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:309:55: ( 'when' !e= expression )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==80) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:309:56: 'when' !e= expression
                    {
                    string_literal99=(Token)match(input,80,FOLLOW_80_in_transitionDeclaration1538); 

                    pushFollow(FOLLOW_expression_in_transitionDeclaration1543);
                    e=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    retval.guard =(e!=null?e.value:null);

                    }
                    break;

            }


            char_literal100=(Token)match(input,29,FOLLOW_29_in_transitionDeclaration1549); 

             retval.value = TamagoCDLEaseFactory.transition((f!=null?f.getText():null),(t!=null?t.getText():null),(w!=null?w.getText():null),retval.guard); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "transitionDeclaration"


    public static class invariantExpression_return extends ParserRuleReturnScope {
        public TInvariant value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "invariantExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:313:1: invariantExpression returns [TInvariant value] : 'invariant' ^e= expression ( 'fail' f= STRING )? ';' ;
    public final TamagoCDLParser.invariantExpression_return invariantExpression() throws RecognitionException {
        TamagoCDLParser.invariantExpression_return retval = new TamagoCDLParser.invariantExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token f=null;
        Token string_literal101=null;
        Token string_literal102=null;
        Token char_literal103=null;
        TamagoCDLParser.expression_return e =null;


        CommonTree f_tree=null;
        CommonTree string_literal101_tree=null;
        CommonTree string_literal102_tree=null;
        CommonTree char_literal103_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:314:2: ( 'invariant' ^e= expression ( 'fail' f= STRING )? ';' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:314:4: 'invariant' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal101=(Token)match(input,53,FOLLOW_53_in_invariantExpression1569); 
            string_literal101_tree = 
            (CommonTree)adaptor.create(string_literal101)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal101_tree, root_0);


            pushFollow(FOLLOW_expression_in_invariantExpression1574);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:314:30: ( 'fail' f= STRING )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==45) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:314:31: 'fail' f= STRING
                    {
                    string_literal102=(Token)match(input,45,FOLLOW_45_in_invariantExpression1577); 
                    string_literal102_tree = 
                    (CommonTree)adaptor.create(string_literal102)
                    ;
                    adaptor.addChild(root_0, string_literal102_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_invariantExpression1581); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal103=(Token)match(input,29,FOLLOW_29_in_invariantExpression1585); 
            char_literal103_tree = 
            (CommonTree)adaptor.create(char_literal103)
            ;
            adaptor.addChild(root_0, char_literal103_tree);


             retval.value = TamagoCDLEaseFactory.invariant((e!=null?e.value:null),(f!=null?f.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "invariantExpression"


    public static class preconditionExpression_return extends ParserRuleReturnScope {
        public TCondition value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "preconditionExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:318:1: preconditionExpression returns [TCondition value] : 'pre' ^e= expression ( 'fail' f= STRING )? ';' ;
    public final TamagoCDLParser.preconditionExpression_return preconditionExpression() throws RecognitionException {
        TamagoCDLParser.preconditionExpression_return retval = new TamagoCDLParser.preconditionExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token f=null;
        Token string_literal104=null;
        Token string_literal105=null;
        Token char_literal106=null;
        TamagoCDLParser.expression_return e =null;


        CommonTree f_tree=null;
        CommonTree string_literal104_tree=null;
        CommonTree string_literal105_tree=null;
        CommonTree char_literal106_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:319:2: ( 'pre' ^e= expression ( 'fail' f= STRING )? ';' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:319:4: 'pre' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal104=(Token)match(input,60,FOLLOW_60_in_preconditionExpression1604); 
            string_literal104_tree = 
            (CommonTree)adaptor.create(string_literal104)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal104_tree, root_0);


            pushFollow(FOLLOW_expression_in_preconditionExpression1609);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:319:24: ( 'fail' f= STRING )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==45) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:319:25: 'fail' f= STRING
                    {
                    string_literal105=(Token)match(input,45,FOLLOW_45_in_preconditionExpression1612); 
                    string_literal105_tree = 
                    (CommonTree)adaptor.create(string_literal105)
                    ;
                    adaptor.addChild(root_0, string_literal105_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_preconditionExpression1616); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal106=(Token)match(input,29,FOLLOW_29_in_preconditionExpression1620); 
            char_literal106_tree = 
            (CommonTree)adaptor.create(char_literal106)
            ;
            adaptor.addChild(root_0, char_literal106_tree);


             retval.value = TamagoCDLEaseFactory.precond((e!=null?e.value:null),(f!=null?f.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "preconditionExpression"


    public static class postconditionExpression_return extends ParserRuleReturnScope {
        public TCondition value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "postconditionExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:323:1: postconditionExpression returns [TCondition value] : 'post' ^e= expression ( 'fail' f= STRING )? ';' ;
    public final TamagoCDLParser.postconditionExpression_return postconditionExpression() throws RecognitionException {
        TamagoCDLParser.postconditionExpression_return retval = new TamagoCDLParser.postconditionExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token f=null;
        Token string_literal107=null;
        Token string_literal108=null;
        Token char_literal109=null;
        TamagoCDLParser.expression_return e =null;


        CommonTree f_tree=null;
        CommonTree string_literal107_tree=null;
        CommonTree string_literal108_tree=null;
        CommonTree char_literal109_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:324:2: ( 'post' ^e= expression ( 'fail' f= STRING )? ';' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:324:4: 'post' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal107=(Token)match(input,59,FOLLOW_59_in_postconditionExpression1640); 
            string_literal107_tree = 
            (CommonTree)adaptor.create(string_literal107)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal107_tree, root_0);


            pushFollow(FOLLOW_expression_in_postconditionExpression1645);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:324:25: ( 'fail' f= STRING )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==45) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:324:26: 'fail' f= STRING
                    {
                    string_literal108=(Token)match(input,45,FOLLOW_45_in_postconditionExpression1648); 
                    string_literal108_tree = 
                    (CommonTree)adaptor.create(string_literal108)
                    ;
                    adaptor.addChild(root_0, string_literal108_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_postconditionExpression1652); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal109=(Token)match(input,29,FOLLOW_29_in_postconditionExpression1656); 
            char_literal109_tree = 
            (CommonTree)adaptor.create(char_literal109)
            ;
            adaptor.addChild(root_0, char_literal109_tree);


             retval.value = TamagoCDLEaseFactory.postcond((e!=null?e.value:null),(f!=null?f.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "postconditionExpression"


    public static class expression_return extends ParserRuleReturnScope {
        public TExpression value;
        public TIOperator opor;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:331:1: expression returns [TExpression value, TIOperator opor] : andExpression ( '||' ^ andExpressionbis )* ;
    public final TamagoCDLParser.expression_return expression() throws RecognitionException {
        TamagoCDLParser.expression_return retval = new TamagoCDLParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal111=null;
        TamagoCDLParser.andExpression_return andExpression110 =null;

        TamagoCDLParser.andExpressionbis_return andExpressionbis112 =null;


        CommonTree string_literal111_tree=null;


        retval.opor = new TIOperator(TOpeName.opOr);

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:341:2: ( andExpression ( '||' ^ andExpressionbis )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:341:4: andExpression ( '||' ^ andExpressionbis )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andExpression_in_expression1687);
            andExpression110=andExpression();

            state._fsp--;

            adaptor.addChild(root_0, andExpression110.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:342:3: ( '||' ^ andExpressionbis )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==84) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:342:4: '||' ^ andExpressionbis
            	    {
            	    string_literal111=(Token)match(input,84,FOLLOW_84_in_expression1693); 
            	    string_literal111_tree = 
            	    (CommonTree)adaptor.create(string_literal111)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal111_tree, root_0);


            	    pushFollow(FOLLOW_andExpressionbis_in_expression1696);
            	    andExpressionbis112=andExpressionbis();

            	    state._fsp--;

            	    adaptor.addChild(root_0, andExpressionbis112.getTree());

            	     retval.opor.addOperand((andExpressionbis112!=null?andExpressionbis112.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


             retval.opor.addOperand((andExpression110!=null?andExpression110.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            	if(retval.opor.size() == 1)
            		retval.value = retval.opor.getOperand(0);
            	else
            		retval.value = retval.opor;

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class andExpressionbis_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpressionbis"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:345:1: andExpressionbis returns [TExpression value] : andExpression ;
    public final TamagoCDLParser.andExpressionbis_return andExpressionbis() throws RecognitionException {
        TamagoCDLParser.andExpressionbis_return retval = new TamagoCDLParser.andExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.andExpression_return andExpression113 =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:346:3: ( andExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:346:3: andExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andExpression_in_andExpressionbis1717);
            andExpression113=andExpression();

            state._fsp--;

            adaptor.addChild(root_0, andExpression113.getTree());

             retval.value = (andExpression113!=null?andExpression113.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andExpressionbis"


    public static class andExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        public TIOperator opand;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:350:1: andExpression returns [TExpression value, TIOperator opand] : relQuantExpression ( '&&' ^ relQuantExpressionbis )* ;
    public final TamagoCDLParser.andExpression_return andExpression() throws RecognitionException {
        TamagoCDLParser.andExpression_return retval = new TamagoCDLParser.andExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal115=null;
        TamagoCDLParser.relQuantExpression_return relQuantExpression114 =null;

        TamagoCDLParser.relQuantExpressionbis_return relQuantExpressionbis116 =null;


        CommonTree string_literal115_tree=null;


        retval.opand = new TIOperator(TOpeName.opAnd);

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:360:2: ( relQuantExpression ( '&&' ^ relQuantExpressionbis )* )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:360:4: relQuantExpression ( '&&' ^ relQuantExpressionbis )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_relQuantExpression_in_andExpression1743);
            relQuantExpression114=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression114.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:361:3: ( '&&' ^ relQuantExpressionbis )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==21) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:361:4: '&&' ^ relQuantExpressionbis
            	    {
            	    string_literal115=(Token)match(input,21,FOLLOW_21_in_andExpression1749); 
            	    string_literal115_tree = 
            	    (CommonTree)adaptor.create(string_literal115)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal115_tree, root_0);


            	    pushFollow(FOLLOW_relQuantExpressionbis_in_andExpression1752);
            	    relQuantExpressionbis116=relQuantExpressionbis();

            	    state._fsp--;

            	    adaptor.addChild(root_0, relQuantExpressionbis116.getTree());

            	     retval.opand.addOperand((relQuantExpressionbis116!=null?relQuantExpressionbis116.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


             retval.opand.addOperand((relQuantExpression114!=null?relQuantExpression114.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            	if(retval.opand.size() == 1)
            		retval.value = retval.opand.getOperand(0);
            	else
            		retval.value = retval.opand;

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andExpression"


    public static class relQuantExpressionbis_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relQuantExpressionbis"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:365:1: relQuantExpressionbis returns [TExpression value] : relQuantExpression ;
    public final TamagoCDLParser.relQuantExpressionbis_return relQuantExpressionbis() throws RecognitionException {
        TamagoCDLParser.relQuantExpressionbis_return retval = new TamagoCDLParser.relQuantExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.relQuantExpression_return relQuantExpression117 =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:366:3: ( relQuantExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:366:3: relQuantExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_relQuantExpression_in_relQuantExpressionbis1775);
            relQuantExpression117=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression117.getTree());

             retval.value = (relQuantExpression117!=null?relQuantExpression117.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relQuantExpressionbis"


    public static class relQuantExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relQuantExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:368:1: relQuantExpression returns [TExpression value] : ( litBoolean | quantExpression | relExpression | notExpression );
    public final TamagoCDLParser.relQuantExpression_return relQuantExpression() throws RecognitionException {
        TamagoCDLParser.relQuantExpression_return retval = new TamagoCDLParser.relQuantExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.litBoolean_return litBoolean118 =null;

        TamagoCDLParser.quantExpression_return quantExpression119 =null;

        TamagoCDLParser.relExpression_return relExpression120 =null;

        TamagoCDLParser.notExpression_return notExpression121 =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:369:2: ( litBoolean | quantExpression | relExpression | notExpression )
            int alt44=4;
            switch ( input.LA(1) ) {
            case 46:
            case 77:
                {
                alt44=1;
                }
                break;
            case QUANTIFIER:
                {
                alt44=2;
                }
                break;
            case FLOAT:
            case ID:
            case INT:
            case STRING:
            case 20:
            case 22:
            case 31:
            case 56:
            case 57:
            case 73:
                {
                alt44=3;
                }
                break;
            case NOTOPERATOR:
                {
                alt44=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }

            switch (alt44) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:370:4: litBoolean
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_litBoolean_in_relQuantExpression1794);
                    litBoolean118=litBoolean();

                    state._fsp--;

                    adaptor.addChild(root_0, litBoolean118.getTree());

                     retval.value =(litBoolean118!=null?litBoolean118.value:null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:371:4: quantExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_quantExpression_in_relQuantExpression1801);
                    quantExpression119=quantExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, quantExpression119.getTree());

                     retval.value =(quantExpression119!=null?quantExpression119.value:null); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:372:4: relExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_relExpression_in_relQuantExpression1808);
                    relExpression120=relExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, relExpression120.getTree());

                     retval.value =(relExpression120!=null?relExpression120.value:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:373:4: notExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_notExpression_in_relQuantExpression1815);
                    notExpression121=notExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, notExpression121.getTree());

                     retval.value =(notExpression121!=null?notExpression121.value:null); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relQuantExpression"


    public static class notExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "notExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:376:1: notExpression returns [TExpression value] : NOTOPERATOR relQuantExpression ;
    public final TamagoCDLParser.notExpression_return notExpression() throws RecognitionException {
        TamagoCDLParser.notExpression_return retval = new TamagoCDLParser.notExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NOTOPERATOR122=null;
        TamagoCDLParser.relQuantExpression_return relQuantExpression123 =null;


        CommonTree NOTOPERATOR122_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:377:2: ( NOTOPERATOR relQuantExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:377:4: NOTOPERATOR relQuantExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            NOTOPERATOR122=(Token)match(input,NOTOPERATOR,FOLLOW_NOTOPERATOR_in_notExpression1832); 
            NOTOPERATOR122_tree = 
            (CommonTree)adaptor.create(NOTOPERATOR122)
            ;
            adaptor.addChild(root_0, NOTOPERATOR122_tree);


            pushFollow(FOLLOW_relQuantExpression_in_notExpression1834);
            relQuantExpression123=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression123.getTree());

             retval.value = new TINot((relQuantExpression123!=null?relQuantExpression123.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "notExpression"


    public static class litBoolean_return extends ParserRuleReturnScope {
        public TBool value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "litBoolean"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:380:1: litBoolean returns [TBool value] : ( 'true' | 'false' );
    public final TamagoCDLParser.litBoolean_return litBoolean() throws RecognitionException {
        TamagoCDLParser.litBoolean_return retval = new TamagoCDLParser.litBoolean_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal124=null;
        Token string_literal125=null;

        CommonTree string_literal124_tree=null;
        CommonTree string_literal125_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:381:2: ( 'true' | 'false' )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==77) ) {
                alt45=1;
            }
            else if ( (LA45_0==46) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:381:4: 'true'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal124=(Token)match(input,77,FOLLOW_77_in_litBoolean1851); 
                    string_literal124_tree = 
                    (CommonTree)adaptor.create(string_literal124)
                    ;
                    adaptor.addChild(root_0, string_literal124_tree);


                     retval.value = new TIBool(true); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:382:4: 'false'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal125=(Token)match(input,46,FOLLOW_46_in_litBoolean1859); 
                    string_literal125_tree = 
                    (CommonTree)adaptor.create(string_literal125)
                    ;
                    adaptor.addChild(root_0, string_literal125_tree);


                     retval.value = new TIBool(false); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "litBoolean"


    public static class quantExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "quantExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:383:1: quantExpression returns [TExpression value] : quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}' ;
    public final TamagoCDLParser.quantExpression_return quantExpression() throws RecognitionException {
        TamagoCDLParser.quantExpression_return retval = new TamagoCDLParser.quantExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token quant=null;
        Token var=null;
        Token char_literal126=null;
        Token char_literal127=null;
        Token char_literal128=null;
        TamagoCDLParser.type_return t =null;

        TamagoCDLParser.domainQuant_return dom =null;

        TamagoCDLParser.expression_return body =null;


        CommonTree quant_tree=null;
        CommonTree var_tree=null;
        CommonTree char_literal126_tree=null;
        CommonTree char_literal127_tree=null;
        CommonTree char_literal128_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:384:2: (quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:384:4: quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            quant=(Token)match(input,QUANTIFIER,FOLLOW_QUANTIFIER_in_quantExpression1875); 
            quant_tree = 
            (CommonTree)adaptor.create(quant)
            ;
            adaptor.addChild(root_0, quant_tree);


            var=(Token)match(input,ID,FOLLOW_ID_in_quantExpression1880); 
            var_tree = 
            (CommonTree)adaptor.create(var)
            ;
            adaptor.addChild(root_0, var_tree);


            char_literal126=(Token)match(input,28,FOLLOW_28_in_quantExpression1883); 
            char_literal126_tree = 
            (CommonTree)adaptor.create(char_literal126)
            ;
            adaptor.addChild(root_0, char_literal126_tree);


            pushFollow(FOLLOW_type_in_quantExpression1888);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            pushFollow(FOLLOW_domainQuant_in_quantExpression1895);
            dom=domainQuant();

            state._fsp--;

            adaptor.addChild(root_0, dom.getTree());

            char_literal127=(Token)match(input,83,FOLLOW_83_in_quantExpression1900); 
            char_literal127_tree = 
            (CommonTree)adaptor.create(char_literal127)
            ;
            adaptor.addChild(root_0, char_literal127_tree);


            pushFollow(FOLLOW_expression_in_quantExpression1906);
            body=expression();

            state._fsp--;

            adaptor.addChild(root_0, body.getTree());

            char_literal128=(Token)match(input,85,FOLLOW_85_in_quantExpression1909); 
            char_literal128_tree = 
            (CommonTree)adaptor.create(char_literal128)
            ;
            adaptor.addChild(root_0, char_literal128_tree);


            retval.value = TamagoCDLEaseFactory.quant((quant!=null?quant.getText():null),(var!=null?var.getText():null),(t!=null?t.value:null),
            			(dom!=null?dom.kind:null),(dom!=null?dom.value:null),(body!=null?body.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "quantExpression"


    public static class domainQuant_return extends ParserRuleReturnScope {
        public String kind;
        public Collection<Object> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "domainQuant"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:398:1: domainQuant returns [String kind, Collection<Object> value] : ( 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression ) | 'from' d= expression 'to' e= expression );
    public final TamagoCDLParser.domainQuant_return domainQuant() throws RecognitionException {
        TamagoCDLParser.domainQuant_return retval = new TamagoCDLParser.domainQuant_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal129=null;
        Token string_literal130=null;
        Token char_literal131=null;
        Token char_literal132=null;
        Token char_literal133=null;
        Token string_literal134=null;
        Token string_literal135=null;
        Token string_literal136=null;
        TamagoCDLParser.expression_return a =null;

        TamagoCDLParser.expression_return b =null;

        TamagoCDLParser.expression_return c =null;

        TamagoCDLParser.expression_return d =null;

        TamagoCDLParser.expression_return e =null;


        CommonTree string_literal129_tree=null;
        CommonTree string_literal130_tree=null;
        CommonTree char_literal131_tree=null;
        CommonTree char_literal132_tree=null;
        CommonTree char_literal133_tree=null;
        CommonTree string_literal134_tree=null;
        CommonTree string_literal135_tree=null;
        CommonTree string_literal136_tree=null;


        retval.value = new ArrayList<Object>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:402:2: ( 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression ) | 'from' d= expression 'to' e= expression )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==50) ) {
                alt48=1;
            }
            else if ( (LA48_0==47) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;

            }
            switch (alt48) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:403:2: 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal129=(Token)match(input,50,FOLLOW_50_in_domainQuant1933); 
                    string_literal129_tree = 
                    (CommonTree)adaptor.create(string_literal129)
                    ;
                    adaptor.addChild(root_0, string_literal129_tree);


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:403:7: ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression )
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==69) ) {
                        alt47=1;
                    }
                    else if ( (LA47_0==41) ) {
                        alt47=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 47, 0, input);

                        throw nvae;

                    }
                    switch (alt47) {
                        case 1 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:403:9: 'set' '[' a= expression ( ',' b= expression )* ']'
                            {
                            string_literal130=(Token)match(input,69,FOLLOW_69_in_domainQuant1937); 
                            string_literal130_tree = 
                            (CommonTree)adaptor.create(string_literal130)
                            ;
                            adaptor.addChild(root_0, string_literal130_tree);


                            char_literal131=(Token)match(input,33,FOLLOW_33_in_domainQuant1939); 
                            char_literal131_tree = 
                            (CommonTree)adaptor.create(char_literal131)
                            ;
                            adaptor.addChild(root_0, char_literal131_tree);


                            pushFollow(FOLLOW_expression_in_domainQuant1943);
                            a=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, a.getTree());

                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:403:32: ( ',' b= expression )*
                            loop46:
                            do {
                                int alt46=2;
                                int LA46_0 = input.LA(1);

                                if ( (LA46_0==25) ) {
                                    alt46=1;
                                }


                                switch (alt46) {
                            	case 1 :
                            	    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:403:33: ',' b= expression
                            	    {
                            	    char_literal132=(Token)match(input,25,FOLLOW_25_in_domainQuant1946); 
                            	    char_literal132_tree = 
                            	    (CommonTree)adaptor.create(char_literal132)
                            	    ;
                            	    adaptor.addChild(root_0, char_literal132_tree);


                            	    pushFollow(FOLLOW_expression_in_domainQuant1950);
                            	    b=expression();

                            	    state._fsp--;

                            	    adaptor.addChild(root_0, b.getTree());

                            	    retval.value.add((b!=null?b.value:null));

                            	    }
                            	    break;

                            	default :
                            	    break loop46;
                                }
                            } while (true);


                            char_literal133=(Token)match(input,35,FOLLOW_35_in_domainQuant1957); 
                            char_literal133_tree = 
                            (CommonTree)adaptor.create(char_literal133)
                            ;
                            adaptor.addChild(root_0, char_literal133_tree);


                             retval.kind = "set"; retval.value.add((a!=null?a.value:null));  

                            }
                            break;
                        case 2 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:404:4: 'coll' c= expression
                            {
                            string_literal134=(Token)match(input,41,FOLLOW_41_in_domainQuant1964); 
                            string_literal134_tree = 
                            (CommonTree)adaptor.create(string_literal134)
                            ;
                            adaptor.addChild(root_0, string_literal134_tree);


                            pushFollow(FOLLOW_expression_in_domainQuant1968);
                            c=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, c.getTree());

                             retval.kind ="coll"; retval.value.add(c.value); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:405:4: 'from' d= expression 'to' e= expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal135=(Token)match(input,47,FOLLOW_47_in_domainQuant1977); 
                    string_literal135_tree = 
                    (CommonTree)adaptor.create(string_literal135)
                    ;
                    adaptor.addChild(root_0, string_literal135_tree);


                    pushFollow(FOLLOW_expression_in_domainQuant1981);
                    d=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, d.getTree());

                    string_literal136=(Token)match(input,74,FOLLOW_74_in_domainQuant1983); 
                    string_literal136_tree = 
                    (CommonTree)adaptor.create(string_literal136)
                    ;
                    adaptor.addChild(root_0, string_literal136_tree);


                    pushFollow(FOLLOW_expression_in_domainQuant1987);
                    e=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    retval.kind ="range"; retval.value.add((d!=null?d.value:null)); retval.value.add((e!=null?e.value:null)); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "domainQuant"


    public static class relExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        public String op;
        public ArrayList<TExpression> exprs;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:408:1: relExpression returns [TExpression value, String op, ArrayList<TExpression> exprs ] : a= arithExpression ( RELOP ^b= arithExpression )? ;
    public final TamagoCDLParser.relExpression_return relExpression() throws RecognitionException {
        TamagoCDLParser.relExpression_return retval = new TamagoCDLParser.relExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token RELOP137=null;
        TamagoCDLParser.arithExpression_return a =null;

        TamagoCDLParser.arithExpression_return b =null;


        CommonTree RELOP137_tree=null;


          retval.exprs = new ArrayList<TExpression>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:415:2: (a= arithExpression ( RELOP ^b= arithExpression )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:415:4: a= arithExpression ( RELOP ^b= arithExpression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_arithExpression_in_relExpression2016);
            a=arithExpression();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:416:3: ( RELOP ^b= arithExpression )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RELOP) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:416:4: RELOP ^b= arithExpression
                    {
                    RELOP137=(Token)match(input,RELOP,FOLLOW_RELOP_in_relExpression2022); 
                    RELOP137_tree = 
                    (CommonTree)adaptor.create(RELOP137)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(RELOP137_tree, root_0);


                    pushFollow(FOLLOW_arithExpression_in_relExpression2029);
                    b=arithExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                    retval.op = (RELOP137!=null?RELOP137.getText():null); retval.exprs.add((b!=null?b.value:null)); 

                    }
                    break;

            }


            retval.exprs.add((a!=null?a.value:null));

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            retval.value = TamagoCDLEaseFactory.operator(retval.op,retval.exprs);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relExpression"


    public static class arithExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        public String op;
        public ArrayList<TExpression> exprs;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arithExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:422:1: arithExpression returns [TExpression value, String op, ArrayList<TExpression> exprs] : multExpression ( ADDOP ^ multExpressionbis )? ;
    public final TamagoCDLParser.arithExpression_return arithExpression() throws RecognitionException {
        TamagoCDLParser.arithExpression_return retval = new TamagoCDLParser.arithExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ADDOP139=null;
        TamagoCDLParser.multExpression_return multExpression138 =null;

        TamagoCDLParser.multExpressionbis_return multExpressionbis140 =null;


        CommonTree ADDOP139_tree=null;


          retval.exprs = new ArrayList<TExpression>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:429:2: ( multExpression ( ADDOP ^ multExpressionbis )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:429:4: multExpression ( ADDOP ^ multExpressionbis )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multExpression_in_arithExpression2066);
            multExpression138=multExpression();

            state._fsp--;

            adaptor.addChild(root_0, multExpression138.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:430:3: ( ADDOP ^ multExpressionbis )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==ADDOP) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:430:4: ADDOP ^ multExpressionbis
                    {
                    ADDOP139=(Token)match(input,ADDOP,FOLLOW_ADDOP_in_arithExpression2072); 
                    ADDOP139_tree = 
                    (CommonTree)adaptor.create(ADDOP139)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ADDOP139_tree, root_0);


                    pushFollow(FOLLOW_multExpressionbis_in_arithExpression2077);
                    multExpressionbis140=multExpressionbis();

                    state._fsp--;

                    adaptor.addChild(root_0, multExpressionbis140.getTree());

                     retval.op = (ADDOP139!=null?ADDOP139.getText():null); retval.exprs.add((multExpressionbis140!=null?multExpressionbis140.value:null)); 

                    }
                    break;

            }


             retval.exprs.add((multExpression138!=null?multExpression138.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            retval.value = TamagoCDLEaseFactory.operator(retval.op,retval.exprs);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arithExpression"


    public static class multExpressionbis_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multExpressionbis"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:434:1: multExpressionbis returns [TExpression value] : multExpression ;
    public final TamagoCDLParser.multExpressionbis_return multExpressionbis() throws RecognitionException {
        TamagoCDLParser.multExpressionbis_return retval = new TamagoCDLParser.multExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.multExpression_return multExpression141 =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:435:3: ( multExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:435:3: multExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multExpression_in_multExpressionbis2099);
            multExpression141=multExpression();

            state._fsp--;

            adaptor.addChild(root_0, multExpression141.getTree());

             retval.value = (multExpression141!=null?multExpression141.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multExpressionbis"


    public static class multExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        public String op;
        public ArrayList<TExpression> exprs;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:437:1: multExpression returns [TExpression value, String op, ArrayList<TExpression> exprs] : unaryExpression ( MULTOP ^ unaryExpressionbis )? ;
    public final TamagoCDLParser.multExpression_return multExpression() throws RecognitionException {
        TamagoCDLParser.multExpression_return retval = new TamagoCDLParser.multExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token MULTOP143=null;
        TamagoCDLParser.unaryExpression_return unaryExpression142 =null;

        TamagoCDLParser.unaryExpressionbis_return unaryExpressionbis144 =null;


        CommonTree MULTOP143_tree=null;


          retval.exprs = new ArrayList<TExpression>();

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:444:2: ( unaryExpression ( MULTOP ^ unaryExpressionbis )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:444:4: unaryExpression ( MULTOP ^ unaryExpressionbis )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExpression_in_multExpression2124);
            unaryExpression142=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression142.getTree());

            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:445:3: ( MULTOP ^ unaryExpressionbis )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==MULTOP) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:445:4: MULTOP ^ unaryExpressionbis
                    {
                    MULTOP143=(Token)match(input,MULTOP,FOLLOW_MULTOP_in_multExpression2129); 
                    MULTOP143_tree = 
                    (CommonTree)adaptor.create(MULTOP143)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MULTOP143_tree, root_0);


                    pushFollow(FOLLOW_unaryExpressionbis_in_multExpression2134);
                    unaryExpressionbis144=unaryExpressionbis();

                    state._fsp--;

                    adaptor.addChild(root_0, unaryExpressionbis144.getTree());

                     retval.op = (MULTOP143!=null?MULTOP143.getText():null); retval.exprs.add((unaryExpressionbis144!=null?unaryExpressionbis144.value:null)); 

                    }
                    break;

            }


             retval.exprs.add((unaryExpression142!=null?unaryExpression142.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            retval.value = TamagoCDLEaseFactory.operator(retval.op,retval.exprs);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multExpression"


    public static class unaryExpressionbis_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpressionbis"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:449:1: unaryExpressionbis returns [TExpression value] : unaryExpression ;
    public final TamagoCDLParser.unaryExpressionbis_return unaryExpressionbis() throws RecognitionException {
        TamagoCDLParser.unaryExpressionbis_return retval = new TamagoCDLParser.unaryExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.unaryExpression_return unaryExpression145 =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:450:2: ( unaryExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:451:2: unaryExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExpression_in_unaryExpressionbis2159);
            unaryExpression145=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression145.getTree());

             retval.value = (unaryExpression145!=null?unaryExpression145.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExpressionbis"


    public static class unaryExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:453:1: unaryExpression returns [TExpression value] : b= primaryExpression ;
    public final TamagoCDLParser.unaryExpression_return unaryExpression() throws RecognitionException {
        TamagoCDLParser.unaryExpression_return retval = new TamagoCDLParser.unaryExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.primaryExpression_return b =null;



        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:454:2: (b= primaryExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:457:2: b= primaryExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_primaryExpression_in_unaryExpression2183);
            b=primaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, b.getTree());

            retval.value =(b!=null?b.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"


    public static class minusArithExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "minusArithExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:459:1: minusArithExpression returns [TExpression value] : '~' a= arithExpression ;
    public final TamagoCDLParser.minusArithExpression_return minusArithExpression() throws RecognitionException {
        TamagoCDLParser.minusArithExpression_return retval = new TamagoCDLParser.minusArithExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal146=null;
        TamagoCDLParser.arithExpression_return a =null;


        CommonTree char_literal146_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:460:2: ( '~' a= arithExpression )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:460:4: '~' a= arithExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal146=(Token)match(input,86,FOLLOW_86_in_minusArithExpression2199); 
            char_literal146_tree = 
            (CommonTree)adaptor.create(char_literal146)
            ;
            adaptor.addChild(root_0, char_literal146_tree);


            pushFollow(FOLLOW_arithExpression_in_minusArithExpression2203);
            a=arithExpression();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

             
            		if((a!=null?a.value:null) instanceof TInteger) {
            			TInteger tin = (TInteger)(a!=null?a.value:null);
            			retval.value = new TIInteger(- tin.getValue() );
            		}
            		else if((a!=null?a.value:null) instanceof TReal) {
            			TReal trea = (TReal)(a!=null?a.value:null);
            			retval.value = new TIReal(- trea.getValue());
            		}
            		else {
            			retval.value = new TIOperator(TOpeName.opTimes);
            			((TIOperator)retval.value).addOperand(new TIInteger(-1));
            			((TIOperator)retval.value).addOperand((a!=null?a.value:null));
            		}
            		

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "minusArithExpression"


    public static class primaryExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primaryExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:476:1: primaryExpression returns [TExpression value] : ( '(' a= expression ')' |b= literalUntypedExpression |c= literalArithExpression |e= atomicExpression );
    public final TamagoCDLParser.primaryExpression_return primaryExpression() throws RecognitionException {
        TamagoCDLParser.primaryExpression_return retval = new TamagoCDLParser.primaryExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal147=null;
        Token char_literal148=null;
        TamagoCDLParser.expression_return a =null;

        TamagoCDLParser.literalUntypedExpression_return b =null;

        TamagoCDLParser.literalArithExpression_return c =null;

        TamagoCDLParser.atomicExpression_return e =null;


        CommonTree char_literal147_tree=null;
        CommonTree char_literal148_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:477:2: ( '(' a= expression ')' |b= literalUntypedExpression |c= literalArithExpression |e= atomicExpression )
            int alt52=4;
            switch ( input.LA(1) ) {
            case 22:
                {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==ID) ) {
                    int LA52_5 = input.LA(3);

                    if ( (LA52_5==23) ) {
                        int LA52_7 = input.LA(4);

                        if ( (LA52_7==ID||LA52_7==20||LA52_7==22||LA52_7==31||LA52_7==73) ) {
                            alt52=4;
                        }
                        else if ( (LA52_7==EOF||LA52_7==ADDOP||LA52_7==MULTOP||LA52_7==RELOP||LA52_7==21||LA52_7==23||LA52_7==25||LA52_7==29||LA52_7==35||LA52_7==45||LA52_7==74||(LA52_7 >= 83 && LA52_7 <= 85)) ) {
                            alt52=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 52, 7, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA52_5==ADDOP||LA52_5==MULTOP||LA52_5==RELOP||(LA52_5 >= 21 && LA52_5 <= 22)||LA52_5==26||LA52_5==33||LA52_5==84) ) {
                        alt52=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 5, input);

                        throw nvae;

                    }
                }
                else if ( (LA52_1==FLOAT||LA52_1==INT||LA52_1==NOTOPERATOR||LA52_1==QUANTIFIER||LA52_1==STRING||LA52_1==20||LA52_1==22||LA52_1==31||LA52_1==46||(LA52_1 >= 56 && LA52_1 <= 57)||LA52_1==73||LA52_1==77) ) {
                    alt52=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 1, input);

                    throw nvae;

                }
                }
                break;
            case 56:
            case 57:
                {
                alt52=2;
                }
                break;
            case FLOAT:
            case INT:
            case STRING:
                {
                alt52=3;
                }
                break;
            case ID:
            case 20:
            case 31:
            case 73:
                {
                alt52=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }

            switch (alt52) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:478:3: '(' a= expression ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal147=(Token)match(input,22,FOLLOW_22_in_primaryExpression2221); 
                    char_literal147_tree = 
                    (CommonTree)adaptor.create(char_literal147)
                    ;
                    adaptor.addChild(root_0, char_literal147_tree);


                    pushFollow(FOLLOW_expression_in_primaryExpression2225);
                    a=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    char_literal148=(Token)match(input,23,FOLLOW_23_in_primaryExpression2227); 
                    char_literal148_tree = 
                    (CommonTree)adaptor.create(char_literal148)
                    ;
                    adaptor.addChild(root_0, char_literal148_tree);


                    retval.value =(a!=null?a.value:null);

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:479:5: b= literalUntypedExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_literalUntypedExpression_in_primaryExpression2237);
                    b=literalUntypedExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                    retval.value =(b!=null?b.value:null); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:480:5: c= literalArithExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_literalArithExpression_in_primaryExpression2247);
                    c=literalArithExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());

                     retval.value =(c!=null?c.value:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:481:5: e= atomicExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atomicExpression_in_primaryExpression2257);
                    e=atomicExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                     retval.value =(e!=null?e.value:null); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "primaryExpression"


    public static class literalUntypedExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literalUntypedExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:484:1: literalUntypedExpression returns [TExpression value] : ( 'null' ^| 'nil' ^);
    public final TamagoCDLParser.literalUntypedExpression_return literalUntypedExpression() throws RecognitionException {
        TamagoCDLParser.literalUntypedExpression_return retval = new TamagoCDLParser.literalUntypedExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal149=null;
        Token string_literal150=null;

        CommonTree string_literal149_tree=null;
        CommonTree string_literal150_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:485:2: ( 'null' ^| 'nil' ^)
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==57) ) {
                alt53=1;
            }
            else if ( (LA53_0==56) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }
            switch (alt53) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:485:4: 'null' ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal149=(Token)match(input,57,FOLLOW_57_in_literalUntypedExpression2275); 
                    string_literal149_tree = 
                    (CommonTree)adaptor.create(string_literal149)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal149_tree, root_0);


                     retval.value =new TINil(); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:485:37: 'nil' ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal150=(Token)match(input,56,FOLLOW_56_in_literalUntypedExpression2281); 
                    string_literal150_tree = 
                    (CommonTree)adaptor.create(string_literal150)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal150_tree, root_0);


                     retval.value =new TINil(); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "literalUntypedExpression"


    public static class literalArithExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literalArithExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:487:1: literalArithExpression returns [TExpression value] : (f= FLOAT |i= INT |s= STRING );
    public final TamagoCDLParser.literalArithExpression_return literalArithExpression() throws RecognitionException {
        TamagoCDLParser.literalArithExpression_return retval = new TamagoCDLParser.literalArithExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token f=null;
        Token i=null;
        Token s=null;

        CommonTree f_tree=null;
        CommonTree i_tree=null;
        CommonTree s_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:488:2: (f= FLOAT |i= INT |s= STRING )
            int alt54=3;
            switch ( input.LA(1) ) {
            case FLOAT:
                {
                alt54=1;
                }
                break;
            case INT:
                {
                alt54=2;
                }
                break;
            case STRING:
                {
                alt54=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }

            switch (alt54) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:489:4: f= FLOAT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_literalArithExpression2304); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                     retval.value = new TIReal(Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:490:4: i= INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    i=(Token)match(input,INT,FOLLOW_INT_in_literalArithExpression2313); 
                    i_tree = 
                    (CommonTree)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);


                     retval.value = new TIInteger(Integer.parseInt((i!=null?i.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:491:4: s= STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_literalArithExpression2323); 
                    s_tree = 
                    (CommonTree)adaptor.create(s)
                    ;
                    adaptor.addChild(root_0, s_tree);


                     retval.value = new TIString((s!=null?s.getText():null)); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "literalArithExpression"


    public static class atomicExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        public Triplet<TExpression, Collection<TExpression>, Boolean> d;
        public String t;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:494:1: atomicExpression returns [TExpression value, Triplet<TExpression, Collection<TExpression>, Boolean> d, String t] : ( '(' p= ID ')' e= atomicExpression | ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )? );
    public final TamagoCDLParser.atomicExpression_return atomicExpression() throws RecognitionException {
        TamagoCDLParser.atomicExpression_return retval = new TamagoCDLParser.atomicExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token p=null;
        Token v=null;
        Token x=null;
        Token char_literal151=null;
        Token char_literal152=null;
        Token string_literal153=null;
        Token char_literal154=null;
        Token string_literal155=null;
        Token char_literal157=null;
        TamagoCDLParser.atomicExpression_return e =null;

        TamagoCDLParser.atomicExpression_return a =null;

        TamagoCDLParser.identSuffix_return identSuffix156 =null;


        CommonTree p_tree=null;
        CommonTree v_tree=null;
        CommonTree x_tree=null;
        CommonTree char_literal151_tree=null;
        CommonTree char_literal152_tree=null;
        CommonTree string_literal153_tree=null;
        CommonTree char_literal154_tree=null;
        CommonTree string_literal155_tree=null;
        CommonTree char_literal157_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:495:2: ( '(' p= ID ')' e= atomicExpression | ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )? )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==22) ) {
                alt58=1;
            }
            else if ( (LA58_0==ID||LA58_0==20||LA58_0==31||LA58_0==73) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;

            }
            switch (alt58) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:496:2: '(' p= ID ')' e= atomicExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal151=(Token)match(input,22,FOLLOW_22_in_atomicExpression2341); 
                    char_literal151_tree = 
                    (CommonTree)adaptor.create(char_literal151)
                    ;
                    adaptor.addChild(root_0, char_literal151_tree);


                    p=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2345); 
                    p_tree = 
                    (CommonTree)adaptor.create(p)
                    ;
                    adaptor.addChild(root_0, p_tree);


                    char_literal152=(Token)match(input,23,FOLLOW_23_in_atomicExpression2347); 
                    char_literal152_tree = 
                    (CommonTree)adaptor.create(char_literal152)
                    ;
                    adaptor.addChild(root_0, char_literal152_tree);


                    pushFollow(FOLLOW_atomicExpression_in_atomicExpression2351);
                    e=atomicExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                     retval.value = TamagoCDLEaseFactory.genCast((p!=null?p.getText():null),(e!=null?e.value:null)); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:498:2: ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:498:2: ( '@return' | '#' v= ID | 'this' |x= ID )
                    int alt55=4;
                    switch ( input.LA(1) ) {
                    case 31:
                        {
                        alt55=1;
                        }
                        break;
                    case 20:
                        {
                        alt55=2;
                        }
                        break;
                    case 73:
                        {
                        alt55=3;
                        }
                        break;
                    case ID:
                        {
                        alt55=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 0, input);

                        throw nvae;

                    }

                    switch (alt55) {
                        case 1 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:498:5: '@return'
                            {
                            string_literal153=(Token)match(input,31,FOLLOW_31_in_atomicExpression2363); 
                            string_literal153_tree = 
                            (CommonTree)adaptor.create(string_literal153)
                            ;
                            adaptor.addChild(root_0, string_literal153_tree);


                             retval.t = "@return"; 

                            }
                            break;
                        case 2 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:499:4: '#' v= ID
                            {
                            char_literal154=(Token)match(input,20,FOLLOW_20_in_atomicExpression2370); 
                            char_literal154_tree = 
                            (CommonTree)adaptor.create(char_literal154)
                            ;
                            adaptor.addChild(root_0, char_literal154_tree);


                            v=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2374); 
                            v_tree = 
                            (CommonTree)adaptor.create(v)
                            ;
                            adaptor.addChild(root_0, v_tree);


                            retval.t = "#"+(v!=null?v.getText():null); 

                            }
                            break;
                        case 3 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:500:3: 'this'
                            {
                            string_literal155=(Token)match(input,73,FOLLOW_73_in_atomicExpression2380); 
                            string_literal155_tree = 
                            (CommonTree)adaptor.create(string_literal155)
                            ;
                            adaptor.addChild(root_0, string_literal155_tree);


                             retval.t = "this"; 

                            }
                            break;
                        case 4 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:501:4: x= ID
                            {
                            x=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2389); 
                            x_tree = 
                            (CommonTree)adaptor.create(x)
                            ;
                            adaptor.addChild(root_0, x_tree);


                             retval.t = (x!=null?x.getText():null); 

                            }
                            break;

                    }


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:503:2: ( identSuffix )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==22||LA56_0==33) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:503:3: identSuffix
                            {
                            pushFollow(FOLLOW_identSuffix_in_atomicExpression2398);
                            identSuffix156=identSuffix();

                            state._fsp--;

                            adaptor.addChild(root_0, identSuffix156.getTree());

                            retval.d =(identSuffix156!=null?identSuffix156.value:null);

                            }
                            break;

                    }


                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:504:2: ( '.' ^a= atomicExpression )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==26) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:504:3: '.' ^a= atomicExpression
                            {
                            char_literal157=(Token)match(input,26,FOLLOW_26_in_atomicExpression2406); 
                            char_literal157_tree = 
                            (CommonTree)adaptor.create(char_literal157)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(char_literal157_tree, root_0);


                            pushFollow(FOLLOW_atomicExpression_in_atomicExpression2411);
                            a=atomicExpression();

                            state._fsp--;

                            adaptor.addChild(root_0, a.getTree());

                             retval.value = (a!=null?a.value:null); 

                            }
                            break;

                    }


                     retval.value = TamagoCDLEaseFactory.genAtomic(retval.t,retval.value,retval.d); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicExpression"


    public static class identSuffix_return extends ParserRuleReturnScope {
        public tamagocc.util.Triplet<TExpression,Collection<TExpression>,Boolean> value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "identSuffix"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:508:1: identSuffix returns [ tamagocc.util.Triplet<TExpression,Collection<TExpression>,Boolean> value] : (a= arrayIndexExpression |b= arguments ) ( '@pre' )? ;
    public final TamagoCDLParser.identSuffix_return identSuffix() throws RecognitionException {
        TamagoCDLParser.identSuffix_return retval = new TamagoCDLParser.identSuffix_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal158=null;
        TamagoCDLParser.arrayIndexExpression_return a =null;

        TamagoCDLParser.arguments_return b =null;


        CommonTree string_literal158_tree=null;


        	retval.value = new Triplet<TExpression,Collection<TExpression>,Boolean>(null,null,new Boolean(false));

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:512:2: ( (a= arrayIndexExpression |b= arguments ) ( '@pre' )? )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:512:4: (a= arrayIndexExpression |b= arguments ) ( '@pre' )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:512:4: (a= arrayIndexExpression |b= arguments )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==33) ) {
                alt59=1;
            }
            else if ( (LA59_0==22) ) {
                alt59=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }
            switch (alt59) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:512:6: a= arrayIndexExpression
                    {
                    pushFollow(FOLLOW_arrayIndexExpression_in_identSuffix2443);
                    a=arrayIndexExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    retval.value.setL((a!=null?a.value:null));

                    }
                    break;
                case 2 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:513:5: b= arguments
                    {
                    pushFollow(FOLLOW_arguments_in_identSuffix2453);
                    b=arguments();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.value.setC((b!=null?b.value:null)) ; 

                    }
                    break;

            }


            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:514:2: ( '@pre' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==30) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:514:3: '@pre'
                    {
                    string_literal158=(Token)match(input,30,FOLLOW_30_in_identSuffix2469); 
                    string_literal158_tree = 
                    (CommonTree)adaptor.create(string_literal158)
                    ;
                    adaptor.addChild(root_0, string_literal158_tree);


                    retval.value.setR(new Boolean(true)); 

                    }
                    break;

            }


             

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "identSuffix"


    public static class arrayIndexExpression_return extends ParserRuleReturnScope {
        public TExpression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayIndexExpression"
    // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:518:1: arrayIndexExpression returns [TExpression value] : '[' i= arithExpression ']' ;
    public final TamagoCDLParser.arrayIndexExpression_return arrayIndexExpression() throws RecognitionException {
        TamagoCDLParser.arrayIndexExpression_return retval = new TamagoCDLParser.arrayIndexExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal159=null;
        Token char_literal160=null;
        TamagoCDLParser.arithExpression_return i =null;


        CommonTree char_literal159_tree=null;
        CommonTree char_literal160_tree=null;

        try {
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:519:2: ( '[' i= arithExpression ']' )
            // C:\\Users\\hakim\\Documents\\recherche\\Tamago\\TamagoCC\\source\\tamagocc\\compiler\\TamagoCDL.g:519:4: '[' i= arithExpression ']'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal159=(Token)match(input,33,FOLLOW_33_in_arrayIndexExpression2492); 
            char_literal159_tree = 
            (CommonTree)adaptor.create(char_literal159)
            ;
            adaptor.addChild(root_0, char_literal159_tree);


            pushFollow(FOLLOW_arithExpression_in_arrayIndexExpression2496);
            i=arithExpression();

            state._fsp--;

            adaptor.addChild(root_0, i.getTree());

            char_literal160=(Token)match(input,35,FOLLOW_35_in_arrayIndexExpression2498); 
            char_literal160_tree = 
            (CommonTree)adaptor.create(char_literal160)
            ;
            adaptor.addChild(root_0, char_literal160_tree);


            retval.value =(i!=null?i.value:null);

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayIndexExpression"

    // Delegated rules


 

    public static final BitSet FOLLOW_moduleDeclaration_in_tamagoEntity80 = new BitSet(new long[]{0x0000040000000000L,0x0000000000004010L});
    public static final BitSet FOLLOW_usingDeclaration_in_tamagoEntity86 = new BitSet(new long[]{0x0000040000000000L,0x0000000000004010L});
    public static final BitSet FOLLOW_serviceEntity_in_tamagoEntity96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_componentEntity_in_tamagoEntity105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_moduleDeclaration129 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_moduleDeclaration134 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_moduleDeclaration136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedName161 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_qualifiedName164 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_qualifiedName169 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedNameWithWildCard200 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_26_in_qualifiedNameWithWildCard203 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_qualifiedNameWithWildCard207 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_27_in_qualifiedNameWithWildCard217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_usingDeclaration241 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedNameWithWildCard_in_usingDeclaration246 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_usingDeclaration248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_percolator265 = new BitSet(new long[]{0x0000000001000400L});
    public static final BitSet FOLLOW_24_in_percolator269 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_ID_in_percolator275 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_percolator280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_require295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_require298 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_require303 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_require305 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_require310 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_require312 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_require317 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_require319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_provide336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_provide339 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_provide344 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_provide346 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_provide351 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_provide353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_serviceEntity373 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_serviceEntity378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_serviceEntity380 = new BitSet(new long[]{0x200A000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_listimplements_in_serviceEntity387 = new BitSet(new long[]{0x2008000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_listrefine_in_serviceEntity393 = new BitSet(new long[]{0x2008000000000000L});
    public static final BitSet FOLLOW_listinclude_in_serviceEntity399 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_listproperties_in_serviceEntity405 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_listinvariants_in_serviceEntity411 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_listmethods_in_serviceEntity417 = new BitSet(new long[]{0x0000004000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_behaviorDeclaration_in_serviceEntity426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_serviceEntity434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_implementsInterface_in_listimplements461 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_refineService_in_listrefine489 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_includeService_in_listinclude515 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_percolator_in_listpercolators549 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_listproperties574 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_provide_in_listprovides600 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_require_in_listrequires624 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_invariantExpression_in_listinvariants648 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_listmethods674 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_42_in_componentEntity701 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_componentEntity706 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_componentEntity708 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_percolator_in_componentEntity715 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_implementsInterface_in_componentEntity723 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_require_in_componentEntity731 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_provide_in_componentEntity739 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_propertyDeclaration_in_componentEntity747 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_invariantExpression_in_componentEntity755 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_methodDeclaration_in_componentEntity763 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_behaviorDeclaration_in_componentEntity772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_componentEntity778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_implementsInterface796 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_implementsInterface801 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_implementsInterface803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_refineService821 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_refineService824 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_refineService829 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_refineService831 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_refineService836 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_refineService838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_refineService852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_refineService855 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_refineService858 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_refineService860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_includeService877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_includeService880 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_includeService885 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_includeService887 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_includeService892 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_includeService894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_includeService908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_includeService911 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_includeService914 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_includeService916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_propertyDeclaration933 = new BitSet(new long[]{0x8000000000000000L,0x0000000000040001L});
    public static final BitSet FOLLOW_accessProperty_in_propertyDeclaration938 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_propertyDeclaration942 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration946 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_propertyDeclaration948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_accessProperty974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_accessProperty981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_accessProperty993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_type1028 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_qualifiedName_in_type1035 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_type1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_primitiveType1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_primitiveType1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_primitiveType1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_primitiveType1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_primitiveType1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_primitiveType1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_primitiveType1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_primitiveType1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_methodDeclaration1151 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_methodDeclaration1156 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration1160 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_parameters_in_methodDeclaration1164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_methodDeclaration1166 = new BitSet(new long[]{0x1801000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_48_in_methodDeclaration1173 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration1178 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_methodDeclaration1180 = new BitSet(new long[]{0x1800000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_preconditionExpression_in_methodDeclaration1191 = new BitSet(new long[]{0x0800000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_postconditionExpression_in_methodDeclaration1201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_methodDeclaration1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_parameters1230 = new BitSet(new long[]{0x0010118100800400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_parameters1235 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_parameters1239 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_25_in_parameters1242 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_parameters1246 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_parameters1250 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_parameters1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_arguments1303 = new BitSet(new long[]{0x0300400080D2AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_arguments1308 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_25_in_arguments1316 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_arguments1320 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_arguments1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_behaviorDeclaration1349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1352 = new BitSet(new long[]{0x0000080000000000L,0x0000000000201080L});
    public static final BitSet FOLLOW_43_in_behaviorDeclaration1357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_behaviorDeclaration1360 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_behaviorDeclaration1365 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_behaviorDeclaration1367 = new BitSet(new long[]{0x0000000000000000L,0x0000000000201080L});
    public static final BitSet FOLLOW_71_in_behaviorDeclaration1376 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200040L});
    public static final BitSet FOLLOW_stateDeclaration_in_behaviorDeclaration1387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200040L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1395 = new BitSet(new long[]{0x0000000000000000L,0x0000000000201000L});
    public static final BitSet FOLLOW_76_in_behaviorDeclaration1402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200800L});
    public static final BitSet FOLLOW_transitionDeclaration_in_behaviorDeclaration1411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200800L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_stateDeclaration1447 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_stateDeclaration1452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_stateDeclaration1454 = new BitSet(new long[]{0x0000001000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_allowDeclaration_in_stateDeclaration1460 = new BitSet(new long[]{0x0000001000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_stateDeclaration1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_allowDeclaration1487 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_allowDeclaration1492 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_allowDeclaration1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_transitionDeclaration1513 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_transitionDeclaration1516 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_transitionDeclaration1523 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_transitionDeclaration1530 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1535 = new BitSet(new long[]{0x0000000020000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_transitionDeclaration1538 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_transitionDeclaration1543 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_transitionDeclaration1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_invariantExpression1569 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_invariantExpression1574 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_invariantExpression1577 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_invariantExpression1581 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_invariantExpression1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_preconditionExpression1604 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_preconditionExpression1609 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_preconditionExpression1612 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_preconditionExpression1616 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_preconditionExpression1620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_postconditionExpression1640 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_postconditionExpression1645 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_postconditionExpression1648 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_postconditionExpression1652 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_postconditionExpression1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_expression1687 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_expression1693 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_andExpressionbis_in_expression1696 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_andExpression_in_andExpressionbis1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relQuantExpression_in_andExpression1743 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_andExpression1749 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_relQuantExpressionbis_in_andExpression1752 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_relQuantExpression_in_relQuantExpressionbis1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_litBoolean_in_relQuantExpression1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantExpression_in_relQuantExpression1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relExpression_in_relQuantExpression1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notExpression_in_relQuantExpression1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTOPERATOR_in_notExpression1832 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_relQuantExpression_in_notExpression1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_litBoolean1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_litBoolean1859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUANTIFIER_in_quantExpression1875 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_quantExpression1880 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_quantExpression1883 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_quantExpression1888 = new BitSet(new long[]{0x0004800000000000L});
    public static final BitSet FOLLOW_domainQuant_in_quantExpression1895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_quantExpression1900 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_quantExpression1906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_quantExpression1909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_domainQuant1933 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_domainQuant1937 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_domainQuant1939 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1943 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_domainQuant1946 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1950 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_domainQuant1957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_domainQuant1964 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_domainQuant1977 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1981 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_domainQuant1983 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arithExpression_in_relExpression2016 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_RELOP_in_relExpression2022 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_relExpression2029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_arithExpression2066 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADDOP_in_arithExpression2072 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_multExpressionbis_in_arithExpression2077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_multExpressionbis2099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multExpression2124 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_MULTOP_in_multExpression2129 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_unaryExpressionbis_in_multExpression2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionbis2159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_unaryExpression2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_minusArithExpression2199 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_minusArithExpression2203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_primaryExpression2221 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_primaryExpression2225 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_primaryExpression2227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalUntypedExpression_in_primaryExpression2237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalArithExpression_in_primaryExpression2247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicExpression_in_primaryExpression2257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_literalUntypedExpression2275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_literalUntypedExpression2281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_literalArithExpression2304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_literalArithExpression2313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literalArithExpression2323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_atomicExpression2341 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2345 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_atomicExpression2347 = new BitSet(new long[]{0x0000000080500400L,0x0000000000000200L});
    public static final BitSet FOLLOW_atomicExpression_in_atomicExpression2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_atomicExpression2363 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_20_in_atomicExpression2370 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2374 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_73_in_atomicExpression2380 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2389 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_identSuffix_in_atomicExpression2398 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_atomicExpression2406 = new BitSet(new long[]{0x0000000080500400L,0x0000000000000200L});
    public static final BitSet FOLLOW_atomicExpression_in_atomicExpression2411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayIndexExpression_in_identSuffix2443 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_arguments_in_identSuffix2453 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_identSuffix2469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_arrayIndexExpression2492 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_arrayIndexExpression2496 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_arrayIndexExpression2498 = new BitSet(new long[]{0x0000000000000002L});

}