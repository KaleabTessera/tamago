// $ANTLR 3.4 /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g 2013-07-10 16:50:02

package tamagocc.compiler;

import tamagocc.api.*;
import tamagocc.impl.*;
import java.util.Collection;
import java.util.ArrayList;
import tamagocc.util.Triplet;
//import tamagocc.exceptions.TamagoCCException;


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
    public String getGrammarFileName() { return "/home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g"; }


    public static class tamagoEntity_return extends ParserRuleReturnScope {
        public TTamagoEntity value;
        public String mod;
        public Collection<TNamespace> uses;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tamagoEntity"
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:63:1: tamagoEntity returns [TTamagoEntity value, String mod, Collection<TNamespace> uses] : m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity ) ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:68:2: (m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity ) )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:68:4: m= moduleDeclaration (u= usingDeclaration )* (s= serviceEntity |c= componentEntity )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_moduleDeclaration_in_tamagoEntity89);
            m=moduleDeclaration();

            state._fsp--;

            adaptor.addChild(root_0, m.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:69:2: (u= usingDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==78) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:69:3: u= usingDeclaration
            	    {
            	    pushFollow(FOLLOW_usingDeclaration_in_tamagoEntity95);
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


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:70:2: (s= serviceEntity |c= componentEntity )
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
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:70:3: s= serviceEntity
                    {
                    pushFollow(FOLLOW_serviceEntity_in_tamagoEntity105);
                    s=serviceEntity();

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());

                     retval.mod =(m!=null?m.value:null); retval.value =(s!=null?s.value:null); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:71:4: c= componentEntity
                    {
                    pushFollow(FOLLOW_componentEntity_in_tamagoEntity114);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:74:1: moduleDeclaration returns [ String value ] : 'module' ^s= qualifiedName ';' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:76:2: ( 'module' ^s= qualifiedName ';' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:76:4: 'module' ^s= qualifiedName ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal1=(Token)match(input,55,FOLLOW_55_in_moduleDeclaration138); 
            string_literal1_tree = 
            (CommonTree)adaptor.create(string_literal1)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal1_tree, root_0);


            pushFollow(FOLLOW_qualifiedName_in_moduleDeclaration143);
            s=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());

            char_literal2=(Token)match(input,29,FOLLOW_29_in_moduleDeclaration145); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:79:1: qualifiedName returns [String value ] : s= ID ( '.' ^d= ID )* ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:83:2: (s= ID ( '.' ^d= ID )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:83:4: s= ID ( '.' ^d= ID )*
            {
            root_0 = (CommonTree)adaptor.nil();


            s=(Token)match(input,ID,FOLLOW_ID_in_qualifiedName170); 
            s_tree = 
            (CommonTree)adaptor.create(s)
            ;
            adaptor.addChild(root_0, s_tree);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:83:9: ( '.' ^d= ID )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==26) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:83:10: '.' ^d= ID
            	    {
            	    char_literal3=(Token)match(input,26,FOLLOW_26_in_qualifiedName173); 
            	    char_literal3_tree = 
            	    (CommonTree)adaptor.create(char_literal3)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal3_tree, root_0);


            	    d=(Token)match(input,ID,FOLLOW_ID_in_qualifiedName178); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:86:1: qualifiedNameWithWildCard returns [String value] : (s= ID ( '.' d= ID )* ( '.*' )? ) ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:2: ( (s= ID ( '.' d= ID )* ( '.*' )? ) )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:4: (s= ID ( '.' d= ID )* ( '.*' )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:4: (s= ID ( '.' d= ID )* ( '.*' )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:6: s= ID ( '.' d= ID )* ( '.*' )?
            {
            s=(Token)match(input,ID,FOLLOW_ID_in_qualifiedNameWithWildCard209); 
            s_tree = 
            (CommonTree)adaptor.create(s)
            ;
            adaptor.addChild(root_0, s_tree);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:11: ( '.' d= ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==26) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:88:12: '.' d= ID
            	    {
            	    char_literal4=(Token)match(input,26,FOLLOW_26_in_qualifiedNameWithWildCard212); 
            	    char_literal4_tree = 
            	    (CommonTree)adaptor.create(char_literal4)
            	    ;
            	    adaptor.addChild(root_0, char_literal4_tree);


            	    d=(Token)match(input,ID,FOLLOW_ID_in_qualifiedNameWithWildCard216); 
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


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:89:2: ( '.*' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==27) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:89:3: '.*'
                    {
                    string_literal5=(Token)match(input,27,FOLLOW_27_in_qualifiedNameWithWildCard226); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:92:1: usingDeclaration returns [TNamespace value] : 'using' ^p= qualifiedNameWithWildCard ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:93:2: ( 'using' ^p= qualifiedNameWithWildCard ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:93:4: 'using' ^p= qualifiedNameWithWildCard ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal6=(Token)match(input,78,FOLLOW_78_in_usingDeclaration250); 
            string_literal6_tree = 
            (CommonTree)adaptor.create(string_literal6)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal6_tree, root_0);


            pushFollow(FOLLOW_qualifiedNameWithWildCard_in_usingDeclaration255);
            p=qualifiedNameWithWildCard();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());

            char_literal7=(Token)match(input,29,FOLLOW_29_in_usingDeclaration257); 

             retval.value = new TINamespace((p!=null?p.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:96:1: percolator returns [TPercolator value] : 'percolator' ^ ( '*' | ID ) ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:97:3: ( 'percolator' ^ ( '*' | ID ) ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:97:3: 'percolator' ^ ( '*' | ID ) ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal8=(Token)match(input,58,FOLLOW_58_in_percolator274); 
            string_literal8_tree = 
            (CommonTree)adaptor.create(string_literal8)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal8_tree, root_0);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:97:17: ( '*' | ID )
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
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:97:18: '*'
                    {
                    char_literal9=(Token)match(input,24,FOLLOW_24_in_percolator278); 
                    char_literal9_tree = 
                    (CommonTree)adaptor.create(char_literal9)
                    ;
                    adaptor.addChild(root_0, char_literal9_tree);


                     retval.value = TIPercolator.getAllPercolator(); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:98:3: ID
                    {
                    ID10=(Token)match(input,ID,FOLLOW_ID_in_percolator284); 
                    ID10_tree = 
                    (CommonTree)adaptor.create(ID10)
                    ;
                    adaptor.addChild(root_0, ID10_tree);


                     retval.value = new TIPercolator((ID10!=null?ID10.getText():null)); 

                    }
                    break;

            }


            char_literal11=(Token)match(input,29,FOLLOW_29_in_percolator289); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:102:1: require returns [TRequire value] : 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:103:3: ( 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:103:3: 'require' ^ 'service' !n= ID 'in' !q= qualifiedName 'as' !l= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal12=(Token)match(input,67,FOLLOW_67_in_require304); 
            string_literal12_tree = 
            (CommonTree)adaptor.create(string_literal12)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal12_tree, root_0);


            string_literal13=(Token)match(input,68,FOLLOW_68_in_require307); 

            n=(Token)match(input,ID,FOLLOW_ID_in_require312); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            string_literal14=(Token)match(input,50,FOLLOW_50_in_require314); 

            pushFollow(FOLLOW_qualifiedName_in_require319);
            q=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, q.getTree());

            string_literal15=(Token)match(input,37,FOLLOW_37_in_require321); 

            l=(Token)match(input,ID,FOLLOW_ID_in_require326); 
            l_tree = 
            (CommonTree)adaptor.create(l)
            ;
            adaptor.addChild(root_0, l_tree);


            char_literal16=(Token)match(input,29,FOLLOW_29_in_require328); 

             retval.value = TamagoCDLEaseFactory.require((n!=null?n.getText():null),(q!=null?q.value:null),(l!=null?l.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:108:1: provide returns [TProvide value] : 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:109:3: ( 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:109:3: 'provide' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal17=(Token)match(input,62,FOLLOW_62_in_provide345); 
            string_literal17_tree = 
            (CommonTree)adaptor.create(string_literal17)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal17_tree, root_0);


            string_literal18=(Token)match(input,68,FOLLOW_68_in_provide348); 

            n=(Token)match(input,ID,FOLLOW_ID_in_provide353); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            string_literal19=(Token)match(input,50,FOLLOW_50_in_provide355); 

            pushFollow(FOLLOW_qualifiedName_in_provide360);
            q=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, q.getTree());

            char_literal20=(Token)match(input,29,FOLLOW_29_in_provide362); 

             retval.value = TamagoCDLEaseFactory.provide((n!=null?n.getText():null),(q!=null?q.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
        public Collection<TImplements> aimpls;
        public Collection<TRefineService> arefs;
        public Collection<TIncludeService> aincs;
        public Collection<TProperty> aprop;
        public Collection<TInvariant> ainvs;
        public Collection<TMethod> ameth;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "serviceEntity"
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:113:1: serviceEntity returns [TService value, TBehavior beh, Collection<TImplements> aimpls, Collection<TRefineService> arefs,\n\tCollection<TIncludeService> aincs, Collection<TProperty> aprop, Collection<TInvariant> ainvs, Collection<TMethod> ameth] : 'service' ^label= ID '{' ! (impls= implementsInterface |refs= refineService |incs= includeService |props= propertyDeclaration |invs= invariantExpression |meths= methodDeclaration )* (b= behaviorDeclaration )? '}' !;
    public final TamagoCDLParser.serviceEntity_return serviceEntity() throws RecognitionException {
        TamagoCDLParser.serviceEntity_return retval = new TamagoCDLParser.serviceEntity_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token label=null;
        Token string_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        TamagoCDLParser.implementsInterface_return impls =null;

        TamagoCDLParser.refineService_return refs =null;

        TamagoCDLParser.includeService_return incs =null;

        TamagoCDLParser.propertyDeclaration_return props =null;

        TamagoCDLParser.invariantExpression_return invs =null;

        TamagoCDLParser.methodDeclaration_return meths =null;

        TamagoCDLParser.behaviorDeclaration_return b =null;


        CommonTree label_tree=null;
        CommonTree string_literal21_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal23_tree=null;


         retval.aimpls = new ArrayList<TImplements>();
         retval.arefs = new ArrayList<TRefineService>();
         retval.aincs = new ArrayList<TIncludeService>();
         retval.aprop = new ArrayList<TProperty>();
         retval.ainvs = new ArrayList<TInvariant>();
         retval.ameth = new ArrayList<TMethod>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:123:2: ( 'service' ^label= ID '{' ! (impls= implementsInterface |refs= refineService |incs= includeService |props= propertyDeclaration |invs= invariantExpression |meths= methodDeclaration )* (b= behaviorDeclaration )? '}' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:124:3: 'service' ^label= ID '{' ! (impls= implementsInterface |refs= refineService |incs= includeService |props= propertyDeclaration |invs= invariantExpression |meths= methodDeclaration )* (b= behaviorDeclaration )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal21=(Token)match(input,68,FOLLOW_68_in_serviceEntity387); 
            string_literal21_tree = 
            (CommonTree)adaptor.create(string_literal21)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal21_tree, root_0);


            label=(Token)match(input,ID,FOLLOW_ID_in_serviceEntity392); 
            label_tree = 
            (CommonTree)adaptor.create(label)
            ;
            adaptor.addChild(root_0, label_tree);


            char_literal22=(Token)match(input,83,FOLLOW_83_in_serviceEntity394); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:125:3: (impls= implementsInterface |refs= refineService |incs= includeService |props= propertyDeclaration |invs= invariantExpression |meths= methodDeclaration )*
            loop7:
            do {
                int alt7=7;
                switch ( input.LA(1) ) {
                case 49:
                    {
                    alt7=1;
                    }
                    break;
                case 66:
                    {
                    alt7=2;
                    }
                    break;
                case 51:
                    {
                    alt7=3;
                    }
                    break;
                case 61:
                    {
                    alt7=4;
                    }
                    break;
                case 53:
                    {
                    alt7=5;
                    }
                    break;
                case 54:
                    {
                    alt7=6;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:126:3: impls= implementsInterface
            	    {
            	    pushFollow(FOLLOW_implementsInterface_in_serviceEntity405);
            	    impls=implementsInterface();

            	    state._fsp--;

            	    adaptor.addChild(root_0, impls.getTree());

            	     retval.aimpls.add((impls!=null?impls.value:null)); 

            	    }
            	    break;
            	case 2 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:127:5: refs= refineService
            	    {
            	    pushFollow(FOLLOW_refineService_in_serviceEntity415);
            	    refs=refineService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, refs.getTree());

            	     retval.arefs.add((refs!=null?refs.value:null)); 

            	    }
            	    break;
            	case 3 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:128:5: incs= includeService
            	    {
            	    pushFollow(FOLLOW_includeService_in_serviceEntity425);
            	    incs=includeService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, incs.getTree());

            	     retval.aincs.add((incs!=null?incs.value:null)); 

            	    }
            	    break;
            	case 4 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:129:5: props= propertyDeclaration
            	    {
            	    pushFollow(FOLLOW_propertyDeclaration_in_serviceEntity435);
            	    props=propertyDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, props.getTree());

            	     retval.aprop.add((props!=null?props.value:null)); 

            	    }
            	    break;
            	case 5 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:130:5: invs= invariantExpression
            	    {
            	    pushFollow(FOLLOW_invariantExpression_in_serviceEntity445);
            	    invs=invariantExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, invs.getTree());

            	     retval.ainvs.add((invs!=null?invs.value:null)); 

            	    }
            	    break;
            	case 6 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:131:5: meths= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_serviceEntity456);
            	    meths=methodDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, meths.getTree());

            	     retval.ameth.add((meths!=null?meths.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:134:3: (b= behaviorDeclaration )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==38) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:134:4: b= behaviorDeclaration
                    {
                    pushFollow(FOLLOW_behaviorDeclaration_in_serviceEntity473);
                    b=behaviorDeclaration();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.beh =(b!=null?b.value:null); 

                    }
                    break;

            }


            char_literal23=(Token)match(input,85,FOLLOW_85_in_serviceEntity481); 


            		retval.value = TamagoCDLEaseFactory.service(retval.value,(label!=null?label.getText():null),retval.aimpls,retval.arefs,retval.aincs,retval.aprop,retval.ainvs,retval.ameth,retval.beh);
            		 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:141:1: listimplements returns [Collection<TImplements> value ] : ( implementsInterface )* ;
    public final TamagoCDLParser.listimplements_return listimplements() throws RecognitionException {
        TamagoCDLParser.listimplements_return retval = new TamagoCDLParser.listimplements_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.implementsInterface_return implementsInterface24 =null;




        	retval.value = new ArrayList<TImplements>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:145:2: ( ( implementsInterface )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:145:4: ( implementsInterface )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:145:4: ( implementsInterface )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==49) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:145:5: implementsInterface
            	    {
            	    pushFollow(FOLLOW_implementsInterface_in_listimplements508);
            	    implementsInterface24=implementsInterface();

            	    state._fsp--;

            	    adaptor.addChild(root_0, implementsInterface24.getTree());

            	     retval.value.add((implementsInterface24!=null?implementsInterface24.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:148:1: listrefine returns [Collection<TRefineService> value ] : ( refineService )* ;
    public final TamagoCDLParser.listrefine_return listrefine() throws RecognitionException {
        TamagoCDLParser.listrefine_return retval = new TamagoCDLParser.listrefine_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.refineService_return refineService25 =null;




        	retval.value = new ArrayList<TRefineService>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:152:2: ( ( refineService )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:152:4: ( refineService )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:152:4: ( refineService )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==66) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:152:5: refineService
            	    {
            	    pushFollow(FOLLOW_refineService_in_listrefine536);
            	    refineService25=refineService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, refineService25.getTree());

            	     retval.value.add((refineService25!=null?refineService25.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:154:1: listinclude returns [Collection<TIncludeService> value ] : ( includeService )* ;
    public final TamagoCDLParser.listinclude_return listinclude() throws RecognitionException {
        TamagoCDLParser.listinclude_return retval = new TamagoCDLParser.listinclude_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.includeService_return includeService26 =null;




        	retval.value = new ArrayList<TIncludeService>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:158:2: ( ( includeService )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:158:4: ( includeService )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:158:4: ( includeService )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==51) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:158:5: includeService
            	    {
            	    pushFollow(FOLLOW_includeService_in_listinclude562);
            	    includeService26=includeService();

            	    state._fsp--;

            	    adaptor.addChild(root_0, includeService26.getTree());

            	     retval.value.add((includeService26!=null?includeService26.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:161:1: listpercolators returns [Collection<TPercolator> value] : (p= percolator )+ ;
    public final TamagoCDLParser.listpercolators_return listpercolators() throws RecognitionException {
        TamagoCDLParser.listpercolators_return retval = new TamagoCDLParser.listpercolators_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.percolator_return p =null;




        retval.value = new ArrayList<TPercolator>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:169:2: ( (p= percolator )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:169:4: (p= percolator )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:169:4: (p= percolator )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==58) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:169:5: p= percolator
            	    {
            	    pushFollow(FOLLOW_percolator_in_listpercolators596);
            	    p=percolator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, p.getTree());

            	     retval.value.add((p!=null?p.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:171:1: listproperties returns [Collection<TProperty> value] : ( propertyDeclaration )+ ;
    public final TamagoCDLParser.listproperties_return listproperties() throws RecognitionException {
        TamagoCDLParser.listproperties_return retval = new TamagoCDLParser.listproperties_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.propertyDeclaration_return propertyDeclaration27 =null;




        retval.value = new ArrayList<TProperty>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:175:2: ( ( propertyDeclaration )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:175:4: ( propertyDeclaration )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:175:4: ( propertyDeclaration )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==61) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:175:5: propertyDeclaration
            	    {
            	    pushFollow(FOLLOW_propertyDeclaration_in_listproperties621);
            	    propertyDeclaration27=propertyDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, propertyDeclaration27.getTree());

            	     retval.value.add((propertyDeclaration27!=null?propertyDeclaration27.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:178:1: listprovides returns [Collection<TProvide> value] : ( provide )+ ;
    public final TamagoCDLParser.listprovides_return listprovides() throws RecognitionException {
        TamagoCDLParser.listprovides_return retval = new TamagoCDLParser.listprovides_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.provide_return provide28 =null;




         retval.value = new ArrayList<TProvide>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:182:2: ( ( provide )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:182:4: ( provide )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:182:4: ( provide )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==62) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:182:5: provide
            	    {
            	    pushFollow(FOLLOW_provide_in_listprovides647);
            	    provide28=provide();

            	    state._fsp--;

            	    adaptor.addChild(root_0, provide28.getTree());

            	     retval.value.add((provide28!=null?provide28.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:184:1: listrequires returns [Collection<TRequire> value] : ( require )+ ;
    public final TamagoCDLParser.listrequires_return listrequires() throws RecognitionException {
        TamagoCDLParser.listrequires_return retval = new TamagoCDLParser.listrequires_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.require_return require29 =null;




         retval.value = new ArrayList<TRequire>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:188:2: ( ( require )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:188:4: ( require )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:188:4: ( require )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==67) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:188:5: require
            	    {
            	    pushFollow(FOLLOW_require_in_listrequires671);
            	    require29=require();

            	    state._fsp--;

            	    adaptor.addChild(root_0, require29.getTree());

            	     retval.value.add((require29!=null?require29.value:null)); 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:190:1: listinvariants returns [ Collection<TInvariant> value] : ( invariantExpression )+ ;
    public final TamagoCDLParser.listinvariants_return listinvariants() throws RecognitionException {
        TamagoCDLParser.listinvariants_return retval = new TamagoCDLParser.listinvariants_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.invariantExpression_return invariantExpression30 =null;




        retval.value = new ArrayList<TInvariant>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:194:2: ( ( invariantExpression )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:194:4: ( invariantExpression )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:194:4: ( invariantExpression )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==53) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:194:5: invariantExpression
            	    {
            	    pushFollow(FOLLOW_invariantExpression_in_listinvariants695);
            	    invariantExpression30=invariantExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, invariantExpression30.getTree());

            	     retval.value.add((invariantExpression30!=null?invariantExpression30.value:null)) ; 

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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:197:1: listmethods returns [ Collection<TMethod> value] : ( methodDeclaration )+ ;
    public final TamagoCDLParser.listmethods_return listmethods() throws RecognitionException {
        TamagoCDLParser.listmethods_return retval = new TamagoCDLParser.listmethods_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.methodDeclaration_return methodDeclaration31 =null;




        retval.value = new ArrayList<TMethod>();

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:201:2: ( ( methodDeclaration )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:201:4: ( methodDeclaration )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:201:4: ( methodDeclaration )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==54) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:201:5: methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_listmethods721);
            	    methodDeclaration31=methodDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, methodDeclaration31.getTree());

            	     retval.value.add((methodDeclaration31!=null?methodDeclaration31.value:null)) ; 

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:205:1: componentEntity returns [TComponent value, TBehavior beh, Collection<TPercolator> aperc, Collection<TImplements> aimpl,\n\tCollection<TRequire> areqs, Collection<TProvide> aprov, Collection<TProperty> aprop, Collection<TInvariant> ainvs,\n\tCollection<TMethod> ameth ] : 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:217:12: ( 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:218:12: 'component' ^label= ID '{' ! (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )* (b= behaviorDeclaration )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal32=(Token)match(input,42,FOLLOW_42_in_componentEntity748); 
            string_literal32_tree = 
            (CommonTree)adaptor.create(string_literal32)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal32_tree, root_0);


            label=(Token)match(input,ID,FOLLOW_ID_in_componentEntity753); 
            label_tree = 
            (CommonTree)adaptor.create(label)
            ;
            adaptor.addChild(root_0, label_tree);


            char_literal33=(Token)match(input,83,FOLLOW_83_in_componentEntity755); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:219:6: (lperc= percolator |limpl= implementsInterface |lreq= require |lprov= provide |lprop= propertyDeclaration |linvs= invariantExpression |lmeth= methodDeclaration )*
            loop18:
            do {
                int alt18=8;
                switch ( input.LA(1) ) {
                case 58:
                    {
                    alt18=1;
                    }
                    break;
                case 49:
                    {
                    alt18=2;
                    }
                    break;
                case 67:
                    {
                    alt18=3;
                    }
                    break;
                case 62:
                    {
                    alt18=4;
                    }
                    break;
                case 61:
                    {
                    alt18=5;
                    }
                    break;
                case 53:
                    {
                    alt18=6;
                    }
                    break;
                case 54:
                    {
                    alt18=7;
                    }
                    break;

                }

                switch (alt18) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:220:6: lperc= percolator
            	    {
            	    pushFollow(FOLLOW_percolator_in_componentEntity762);
            	    lperc=percolator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lperc.getTree());

            	     retval.aperc.add((lperc!=null?lperc.value:null)); 

            	    }
            	    break;
            	case 2 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:221:6: limpl= implementsInterface
            	    {
            	    pushFollow(FOLLOW_implementsInterface_in_componentEntity770);
            	    limpl=implementsInterface();

            	    state._fsp--;

            	    adaptor.addChild(root_0, limpl.getTree());

            	     retval.aimpl.add((limpl!=null?limpl.value:null)); 

            	    }
            	    break;
            	case 3 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:222:5: lreq= require
            	    {
            	    pushFollow(FOLLOW_require_in_componentEntity778);
            	    lreq=require();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lreq.getTree());

            	     retval.areqs.add((lreq!=null?lreq.value:null)); 

            	    }
            	    break;
            	case 4 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:223:6: lprov= provide
            	    {
            	    pushFollow(FOLLOW_provide_in_componentEntity786);
            	    lprov=provide();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lprov.getTree());

            	     retval.aprov.add((lprov!=null?lprov.value:null)); 

            	    }
            	    break;
            	case 5 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:224:6: lprop= propertyDeclaration
            	    {
            	    pushFollow(FOLLOW_propertyDeclaration_in_componentEntity794);
            	    lprop=propertyDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lprop.getTree());

            	     retval.aprop.add((lprop!=null?lprop.value:null)); 

            	    }
            	    break;
            	case 6 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:225:6: linvs= invariantExpression
            	    {
            	    pushFollow(FOLLOW_invariantExpression_in_componentEntity802);
            	    linvs=invariantExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, linvs.getTree());

            	     retval.ainvs.add((linvs!=null?linvs.value:null)); 

            	    }
            	    break;
            	case 7 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:226:6: lmeth= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_componentEntity810);
            	    lmeth=methodDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, lmeth.getTree());

            	     retval.ameth.add((lmeth!=null?lmeth.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:227:2: (b= behaviorDeclaration )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==38) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:227:2: b= behaviorDeclaration
                    {
                    pushFollow(FOLLOW_behaviorDeclaration_in_componentEntity819);
                    b=behaviorDeclaration();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.beh =(b!=null?b.value:null); 

                    }
                    break;

            }


            char_literal34=(Token)match(input,85,FOLLOW_85_in_componentEntity825); 

             retval.value = TamagoCDLEaseFactory.component(retval.value,(label!=null?label.getText():null),retval.aimpl,retval.aprov,retval.areqs,retval.aprop,retval.ainvs,retval.ameth,retval.beh,retval.aperc);

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:232:1: implementsInterface returns [TImplements value] : 'implements' ^p= qualifiedName ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:233:2: ( 'implements' ^p= qualifiedName ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:233:4: 'implements' ^p= qualifiedName ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal35=(Token)match(input,49,FOLLOW_49_in_implementsInterface843); 
            string_literal35_tree = 
            (CommonTree)adaptor.create(string_literal35)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal35_tree, root_0);


            pushFollow(FOLLOW_qualifiedName_in_implementsInterface848);
            p=qualifiedName();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());

            char_literal36=(Token)match(input,29,FOLLOW_29_in_implementsInterface850); 

             retval.value = new TIImplements((p!=null?p.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:236:1: refineService returns [TRefineService value] : ( 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'refine' ^ 'service' ! qualifiedName ';' !);
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:237:2: ( 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'refine' ^ 'service' ! qualifiedName ';' !)
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==66) ) {
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
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:237:4: 'refine' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal37=(Token)match(input,66,FOLLOW_66_in_refineService868); 
                    string_literal37_tree = 
                    (CommonTree)adaptor.create(string_literal37)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal37_tree, root_0);


                    string_literal38=(Token)match(input,68,FOLLOW_68_in_refineService871); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_refineService876); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    string_literal39=(Token)match(input,50,FOLLOW_50_in_refineService878); 

                    pushFollow(FOLLOW_qualifiedName_in_refineService883);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    char_literal40=(Token)match(input,29,FOLLOW_29_in_refineService885); 

                     retval.value = TamagoCDLEaseFactory.refine((n!=null?n.getText():null),(q!=null?q.value:null));  

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:238:10: 'refine' ^ 'service' ! qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal41=(Token)match(input,66,FOLLOW_66_in_refineService899); 
                    string_literal41_tree = 
                    (CommonTree)adaptor.create(string_literal41)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal41_tree, root_0);


                    string_literal42=(Token)match(input,68,FOLLOW_68_in_refineService902); 

                    pushFollow(FOLLOW_qualifiedName_in_refineService905);
                    qualifiedName43=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedName43.getTree());

                    char_literal44=(Token)match(input,29,FOLLOW_29_in_refineService907); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:241:1: includeService returns [TIncludeService value] : ( 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'include' ^ 'service' ! qualifiedName ';' !);
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:242:2: ( 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !| 'include' ^ 'service' ! qualifiedName ';' !)
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==51) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==68) ) {
                    int LA21_2 = input.LA(3);

                    if ( (LA21_2==ID) ) {
                        int LA21_3 = input.LA(4);

                        if ( (LA21_3==50) ) {
                            alt21=1;
                        }
                        else if ( (LA21_3==26||LA21_3==29) ) {
                            alt21=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 21, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:242:4: 'include' ^ 'service' !n= ID 'in' !q= qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal45=(Token)match(input,51,FOLLOW_51_in_includeService924); 
                    string_literal45_tree = 
                    (CommonTree)adaptor.create(string_literal45)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal45_tree, root_0);


                    string_literal46=(Token)match(input,68,FOLLOW_68_in_includeService927); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_includeService932); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    string_literal47=(Token)match(input,50,FOLLOW_50_in_includeService934); 

                    pushFollow(FOLLOW_qualifiedName_in_includeService939);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    char_literal48=(Token)match(input,29,FOLLOW_29_in_includeService941); 

                     retval.value = TamagoCDLEaseFactory.include((n!=null?n.getText():null),(q!=null?q.value:null)); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:243:10: 'include' ^ 'service' ! qualifiedName ';' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal49=(Token)match(input,51,FOLLOW_51_in_includeService955); 
                    string_literal49_tree = 
                    (CommonTree)adaptor.create(string_literal49)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal49_tree, root_0);


                    string_literal50=(Token)match(input,68,FOLLOW_68_in_includeService958); 

                    pushFollow(FOLLOW_qualifiedName_in_includeService961);
                    qualifiedName51=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedName51.getTree());

                    char_literal52=(Token)match(input,29,FOLLOW_29_in_includeService963); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:246:1: propertyDeclaration returns [TProperty value ] : 'property' ^a= accessProperty t= type n= ID ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:247:2: ( 'property' ^a= accessProperty t= type n= ID ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:247:4: 'property' ^a= accessProperty t= type n= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal53=(Token)match(input,61,FOLLOW_61_in_propertyDeclaration980); 
            string_literal53_tree = 
            (CommonTree)adaptor.create(string_literal53)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal53_tree, root_0);


            pushFollow(FOLLOW_accessProperty_in_propertyDeclaration985);
            a=accessProperty();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            pushFollow(FOLLOW_type_in_propertyDeclaration989);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration993); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            char_literal54=(Token)match(input,29,FOLLOW_29_in_propertyDeclaration995); 

             
            			retval.value = new TIProperty((n!=null?n.getText():null),(t!=null?t.value:null),(a!=null?a.value:null)); 
            		

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:253:1: accessProperty returns [TAccess value] : ( 'readwrite' | 'read' | 'write' );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:254:2: ( 'readwrite' | 'read' | 'write' )
            int alt22=3;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt22=1;
                }
                break;
            case 63:
                {
                alt22=2;
                }
                break;
            case 82:
                {
                alt22=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:255:4: 'readwrite'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal55=(Token)match(input,64,FOLLOW_64_in_accessProperty1021); 
                    string_literal55_tree = 
                    (CommonTree)adaptor.create(string_literal55)
                    ;
                    adaptor.addChild(root_0, string_literal55_tree);


                     retval.value = new TIAccess("rw");  

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:256:4: 'read'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal56=(Token)match(input,63,FOLLOW_63_in_accessProperty1028); 
                    string_literal56_tree = 
                    (CommonTree)adaptor.create(string_literal56)
                    ;
                    adaptor.addChild(root_0, string_literal56_tree);


                     retval.value = new TIAccess("r"); 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:257:4: 'write'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal57=(Token)match(input,82,FOLLOW_82_in_accessProperty1040); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:260:1: type returns [ TType value, String prim, boolean flags] : (p= primitiveType |q= qualifiedName ) ( '[]' )? ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:271:2: ( (p= primitiveType |q= qualifiedName ) ( '[]' )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:271:4: (p= primitiveType |q= qualifiedName ) ( '[]' )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:271:4: (p= primitiveType |q= qualifiedName )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==32||(LA23_0 >= 39 && LA23_0 <= 40)||LA23_0==44||LA23_0==52||LA23_0==65||LA23_0==72||LA23_0==79) ) {
                alt23=1;
            }
            else if ( (LA23_0==ID) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:271:5: p= primitiveType
                    {
                    pushFollow(FOLLOW_primitiveType_in_type1075);
                    p=primitiveType();

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    retval.prim =(p!=null?p.value:null); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:271:41: q= qualifiedName
                    {
                    pushFollow(FOLLOW_qualifiedName_in_type1082);
                    q=qualifiedName();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    retval.prim =(q!=null?q.value:null);

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:272:3: ( '[]' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==34) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:272:4: '[]'
                    {
                    string_literal58=(Token)match(input,34,FOLLOW_34_in_type1091); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:275:1: primitiveType returns [String value ] : ( 'int' | 'void' | 'bool' | 'boolean' | 'real' | 'double' | 'String' | 'string' );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:276:2: ( 'int' | 'void' | 'bool' | 'boolean' | 'real' | 'double' | 'String' | 'string' )
            int alt25=8;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt25=1;
                }
                break;
            case 79:
                {
                alt25=2;
                }
                break;
            case 39:
                {
                alt25=3;
                }
                break;
            case 40:
                {
                alt25=4;
                }
                break;
            case 65:
                {
                alt25=5;
                }
                break;
            case 44:
                {
                alt25=6;
                }
                break;
            case 32:
                {
                alt25=7;
                }
                break;
            case 72:
                {
                alt25=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:276:4: 'int'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal59=(Token)match(input,52,FOLLOW_52_in_primitiveType1115); 
                    string_literal59_tree = 
                    (CommonTree)adaptor.create(string_literal59)
                    ;
                    adaptor.addChild(root_0, string_literal59_tree);


                     retval.value ="int"; 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:277:4: 'void'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal60=(Token)match(input,79,FOLLOW_79_in_primitiveType1123); 
                    string_literal60_tree = 
                    (CommonTree)adaptor.create(string_literal60)
                    ;
                    adaptor.addChild(root_0, string_literal60_tree);


                     retval.value ="void"; 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:278:4: 'bool'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal61=(Token)match(input,39,FOLLOW_39_in_primitiveType1132); 
                    string_literal61_tree = 
                    (CommonTree)adaptor.create(string_literal61)
                    ;
                    adaptor.addChild(root_0, string_literal61_tree);


                     retval.value ="bool"; 

                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:279:4: 'boolean'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal62=(Token)match(input,40,FOLLOW_40_in_primitiveType1141); 
                    string_literal62_tree = 
                    (CommonTree)adaptor.create(string_literal62)
                    ;
                    adaptor.addChild(root_0, string_literal62_tree);


                     retval.value ="bool"; 

                    }
                    break;
                case 5 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:280:4: 'real'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal63=(Token)match(input,65,FOLLOW_65_in_primitiveType1150); 
                    string_literal63_tree = 
                    (CommonTree)adaptor.create(string_literal63)
                    ;
                    adaptor.addChild(root_0, string_literal63_tree);


                     retval.value ="real"; 

                    }
                    break;
                case 6 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:281:4: 'double'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal64=(Token)match(input,44,FOLLOW_44_in_primitiveType1159); 
                    string_literal64_tree = 
                    (CommonTree)adaptor.create(string_literal64)
                    ;
                    adaptor.addChild(root_0, string_literal64_tree);


                     retval.value ="real"; 

                    }
                    break;
                case 7 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:282:4: 'String'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal65=(Token)match(input,32,FOLLOW_32_in_primitiveType1168); 
                    string_literal65_tree = 
                    (CommonTree)adaptor.create(string_literal65)
                    ;
                    adaptor.addChild(root_0, string_literal65_tree);


                     retval.value ="String"; 

                    }
                    break;
                case 8 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:283:4: 'string'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal66=(Token)match(input,72,FOLLOW_72_in_primitiveType1177); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:286:1: methodDeclaration returns [TMethod value] : 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:287:2: ( 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:288:3: 'method' ^t= type n= ID a= parameters '{' ! ( 'id' ^d= ID ';' !)? (p= preconditionExpression )? (q= postconditionExpression )? '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal67=(Token)match(input,54,FOLLOW_54_in_methodDeclaration1198); 
            string_literal67_tree = 
            (CommonTree)adaptor.create(string_literal67)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal67_tree, root_0);


            pushFollow(FOLLOW_type_in_methodDeclaration1203);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration1207); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            pushFollow(FOLLOW_parameters_in_methodDeclaration1211);
            a=parameters();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            char_literal68=(Token)match(input,83,FOLLOW_83_in_methodDeclaration1213); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:289:4: ( 'id' ^d= ID ';' !)?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==48) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:289:5: 'id' ^d= ID ';' !
                    {
                    string_literal69=(Token)match(input,48,FOLLOW_48_in_methodDeclaration1220); 
                    string_literal69_tree = 
                    (CommonTree)adaptor.create(string_literal69)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal69_tree, root_0);


                    d=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration1225); 
                    d_tree = 
                    (CommonTree)adaptor.create(d)
                    ;
                    adaptor.addChild(root_0, d_tree);


                    char_literal70=(Token)match(input,29,FOLLOW_29_in_methodDeclaration1227); 

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:290:4: (p= preconditionExpression )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==60) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:290:5: p= preconditionExpression
                    {
                    pushFollow(FOLLOW_preconditionExpression_in_methodDeclaration1238);
                    p=preconditionExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:291:4: (q= postconditionExpression )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==59) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:291:5: q= postconditionExpression
                    {
                    pushFollow(FOLLOW_postconditionExpression_in_methodDeclaration1248);
                    q=postconditionExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, q.getTree());

                    }
                    break;

            }


            char_literal71=(Token)match(input,85,FOLLOW_85_in_methodDeclaration1254); 

            retval.value = TamagoCDLEaseFactory.method((t!=null?t.value:null), (n!=null?n.getText():null), (a!=null?a.params:null), (d!=null?d.getText():null), (p!=null?p.value:null), (q!=null?q.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:296:1: parameters returns [Collection<TParameter> params] : '(' (a= type b= ID ( ',' c= type d= ID )* )? ')' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:2: ( '(' (a= type b= ID ( ',' c= type d= ID )* )? ')' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:4: '(' (a= type b= ID ( ',' c= type d= ID )* )? ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal72=(Token)match(input,22,FOLLOW_22_in_parameters1277); 
            char_literal72_tree = 
            (CommonTree)adaptor.create(char_literal72)
            ;
            adaptor.addChild(root_0, char_literal72_tree);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:8: (a= type b= ID ( ',' c= type d= ID )* )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==ID||LA30_0==32||(LA30_0 >= 39 && LA30_0 <= 40)||LA30_0==44||LA30_0==52||LA30_0==65||LA30_0==72||LA30_0==79) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:9: a= type b= ID ( ',' c= type d= ID )*
                    {
                    pushFollow(FOLLOW_type_in_parameters1282);
                    a=type();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    b=(Token)match(input,ID,FOLLOW_ID_in_parameters1286); 
                    b_tree = 
                    (CommonTree)adaptor.create(b)
                    ;
                    adaptor.addChild(root_0, b_tree);


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:21: ( ',' c= type d= ID )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==25) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:300:22: ',' c= type d= ID
                    	    {
                    	    char_literal73=(Token)match(input,25,FOLLOW_25_in_parameters1289); 
                    	    char_literal73_tree = 
                    	    (CommonTree)adaptor.create(char_literal73)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal73_tree);


                    	    pushFollow(FOLLOW_type_in_parameters1293);
                    	    c=type();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, c.getTree());

                    	    d=(Token)match(input,ID,FOLLOW_ID_in_parameters1297); 
                    	    d_tree = 
                    	    (CommonTree)adaptor.create(d)
                    	    ;
                    	    adaptor.addChild(root_0, d_tree);


                    	     retval.params.add(TamagoCDLEaseFactory.param((c!=null?c.value:null),(d!=null?d.getText():null))); 

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                     retval.params.add(TamagoCDLEaseFactory.param((a!=null?a.value:null),(b!=null?b.getText():null))); 

                    }
                    break;

            }


            char_literal74=(Token)match(input,23,FOLLOW_23_in_parameters1328); 
            char_literal74_tree = 
            (CommonTree)adaptor.create(char_literal74)
            ;
            adaptor.addChild(root_0, char_literal74_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:308:1: arguments returns [Collection<TExpression> value] : '(' (a= expression ( ',' b= expression )* )? ')' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:312:2: ( '(' (a= expression ( ',' b= expression )* )? ')' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:312:4: '(' (a= expression ( ',' b= expression )* )? ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal75=(Token)match(input,22,FOLLOW_22_in_arguments1350); 
            char_literal75_tree = 
            (CommonTree)adaptor.create(char_literal75)
            ;
            adaptor.addChild(root_0, char_literal75_tree);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:312:8: (a= expression ( ',' b= expression )* )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==FLOAT||(LA32_0 >= ID && LA32_0 <= INT)||LA32_0==NOTOPERATOR||LA32_0==QUANTIFIER||LA32_0==STRING||LA32_0==20||LA32_0==22||LA32_0==31||LA32_0==46||(LA32_0 >= 56 && LA32_0 <= 57)||LA32_0==73||LA32_0==77) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:312:9: a= expression ( ',' b= expression )*
                    {
                    pushFollow(FOLLOW_expression_in_arguments1355);
                    a=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    retval.value.add((a!=null?a.value:null));

                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:313:4: ( ',' b= expression )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==25) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:313:5: ',' b= expression
                    	    {
                    	    char_literal76=(Token)match(input,25,FOLLOW_25_in_arguments1363); 
                    	    char_literal76_tree = 
                    	    (CommonTree)adaptor.create(char_literal76)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal76_tree);


                    	    pushFollow(FOLLOW_expression_in_arguments1367);
                    	    b=expression();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, b.getTree());

                    	     retval.value.add((b!=null?b.value:null)); 

                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal77=(Token)match(input,23,FOLLOW_23_in_arguments1375); 
            char_literal77_tree = 
            (CommonTree)adaptor.create(char_literal77)
            ;
            adaptor.addChild(root_0, char_literal77_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:316:1: behaviorDeclaration returns [TIBehavior value, String def, Collection<TState> states, Collection<TTransition> transitions ] : 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:323:2: ( 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:323:4: 'behavior' ^ '{' ! ( 'default' ^ 'state' !n= ID ';' !)? ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)? ( 'transitions' '{' (t= transitionDeclaration )* '}' )? '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal78=(Token)match(input,38,FOLLOW_38_in_behaviorDeclaration1396); 
            string_literal78_tree = 
            (CommonTree)adaptor.create(string_literal78)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal78_tree, root_0);


            char_literal79=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1399); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:324:2: ( 'default' ^ 'state' !n= ID ';' !)?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==43) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:324:3: 'default' ^ 'state' !n= ID ';' !
                    {
                    string_literal80=(Token)match(input,43,FOLLOW_43_in_behaviorDeclaration1404); 
                    string_literal80_tree = 
                    (CommonTree)adaptor.create(string_literal80)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal80_tree, root_0);


                    string_literal81=(Token)match(input,70,FOLLOW_70_in_behaviorDeclaration1407); 

                    n=(Token)match(input,ID,FOLLOW_ID_in_behaviorDeclaration1412); 
                    n_tree = 
                    (CommonTree)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal82=(Token)match(input,29,FOLLOW_29_in_behaviorDeclaration1414); 

                    retval.def = (n!=null?n.getText():null);

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:325:2: ( 'states' ^ '{' ! (s= stateDeclaration )* '}' !)?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==71) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:325:3: 'states' ^ '{' ! (s= stateDeclaration )* '}' !
                    {
                    string_literal83=(Token)match(input,71,FOLLOW_71_in_behaviorDeclaration1423); 
                    string_literal83_tree = 
                    (CommonTree)adaptor.create(string_literal83)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal83_tree, root_0);


                    char_literal84=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1426); 

                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:326:3: (s= stateDeclaration )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==70) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:326:4: s= stateDeclaration
                    	    {
                    	    pushFollow(FOLLOW_stateDeclaration_in_behaviorDeclaration1434);
                    	    s=stateDeclaration();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, s.getTree());

                    	    retval.states.add((s!=null?s.value:null)); 

                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    char_literal85=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1442); 

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:328:2: ( 'transitions' '{' (t= transitionDeclaration )* '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==76) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:328:3: 'transitions' '{' (t= transitionDeclaration )* '}'
                    {
                    string_literal86=(Token)match(input,76,FOLLOW_76_in_behaviorDeclaration1449); 
                    string_literal86_tree = 
                    (CommonTree)adaptor.create(string_literal86)
                    ;
                    adaptor.addChild(root_0, string_literal86_tree);


                    char_literal87=(Token)match(input,83,FOLLOW_83_in_behaviorDeclaration1451); 
                    char_literal87_tree = 
                    (CommonTree)adaptor.create(char_literal87)
                    ;
                    adaptor.addChild(root_0, char_literal87_tree);


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:329:3: (t= transitionDeclaration )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==75) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:329:4: t= transitionDeclaration
                    	    {
                    	    pushFollow(FOLLOW_transitionDeclaration_in_behaviorDeclaration1458);
                    	    t=transitionDeclaration();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, t.getTree());

                    	    retval.transitions.add((t!=null?t.value:null));

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    char_literal88=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1465); 
                    char_literal88_tree = 
                    (CommonTree)adaptor.create(char_literal88)
                    ;
                    adaptor.addChild(root_0, char_literal88_tree);


                    }
                    break;

            }


            char_literal89=(Token)match(input,85,FOLLOW_85_in_behaviorDeclaration1470); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:334:1: stateDeclaration returns [ Collection<TAllow> allows, TState value] : 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:339:2: ( 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:339:4: 'state' ^i= ID '{' ! (a= allowDeclaration )* '}' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal90=(Token)match(input,70,FOLLOW_70_in_stateDeclaration1494); 
            string_literal90_tree = 
            (CommonTree)adaptor.create(string_literal90)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal90_tree, root_0);


            i=(Token)match(input,ID,FOLLOW_ID_in_stateDeclaration1499); 
            i_tree = 
            (CommonTree)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            char_literal91=(Token)match(input,83,FOLLOW_83_in_stateDeclaration1501); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:339:23: (a= allowDeclaration )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==36) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:339:24: a= allowDeclaration
            	    {
            	    pushFollow(FOLLOW_allowDeclaration_in_stateDeclaration1507);
            	    a=allowDeclaration();

            	    state._fsp--;

            	    adaptor.addChild(root_0, a.getTree());

            	     retval.allows.add((a!=null?a.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            char_literal92=(Token)match(input,85,FOLLOW_85_in_stateDeclaration1513); 

              retval.value = new TIState((i!=null?i.getText():null), retval.allows);  

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:343:1: allowDeclaration returns [TAllow value] : 'allow' ^n= ID ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:344:2: ( 'allow' ^n= ID ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:344:4: 'allow' ^n= ID ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal93=(Token)match(input,36,FOLLOW_36_in_allowDeclaration1534); 
            string_literal93_tree = 
            (CommonTree)adaptor.create(string_literal93)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal93_tree, root_0);


            n=(Token)match(input,ID,FOLLOW_ID_in_allowDeclaration1539); 
            n_tree = 
            (CommonTree)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            char_literal94=(Token)match(input,29,FOLLOW_29_in_allowDeclaration1541); 

            retval.value = new TIAllow((n!=null?n.getText():null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:347:1: transitionDeclaration returns [TTransition value, TExpression guard] : 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:349:2: ( 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !)
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:349:4: 'transition' ^ 'from' !f= ID 'to' !t= ID 'with' !w= ID ( 'when' !e= expression )? ';' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal95=(Token)match(input,75,FOLLOW_75_in_transitionDeclaration1560); 
            string_literal95_tree = 
            (CommonTree)adaptor.create(string_literal95)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal95_tree, root_0);


            string_literal96=(Token)match(input,47,FOLLOW_47_in_transitionDeclaration1563); 

            f=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1568); 
            f_tree = 
            (CommonTree)adaptor.create(f)
            ;
            adaptor.addChild(root_0, f_tree);


            string_literal97=(Token)match(input,74,FOLLOW_74_in_transitionDeclaration1570); 

            t=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1575); 
            t_tree = 
            (CommonTree)adaptor.create(t)
            ;
            adaptor.addChild(root_0, t_tree);


            string_literal98=(Token)match(input,81,FOLLOW_81_in_transitionDeclaration1577); 

            w=(Token)match(input,ID,FOLLOW_ID_in_transitionDeclaration1582); 
            w_tree = 
            (CommonTree)adaptor.create(w)
            ;
            adaptor.addChild(root_0, w_tree);


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:349:55: ( 'when' !e= expression )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==80) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:349:56: 'when' !e= expression
                    {
                    string_literal99=(Token)match(input,80,FOLLOW_80_in_transitionDeclaration1585); 

                    pushFollow(FOLLOW_expression_in_transitionDeclaration1590);
                    e=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    retval.guard =(e!=null?e.value:null);

                    }
                    break;

            }


            char_literal100=(Token)match(input,29,FOLLOW_29_in_transitionDeclaration1596); 

             retval.value = TamagoCDLEaseFactory.transition((f!=null?f.getText():null),(t!=null?t.getText():null),(w!=null?w.getText():null),retval.guard); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:353:1: invariantExpression returns [TInvariant value] : 'invariant' ^e= expression ( 'fail' f= STRING )? ';' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:354:2: ( 'invariant' ^e= expression ( 'fail' f= STRING )? ';' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:354:4: 'invariant' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal101=(Token)match(input,53,FOLLOW_53_in_invariantExpression1616); 
            string_literal101_tree = 
            (CommonTree)adaptor.create(string_literal101)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal101_tree, root_0);


            pushFollow(FOLLOW_expression_in_invariantExpression1621);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:354:30: ( 'fail' f= STRING )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==45) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:354:31: 'fail' f= STRING
                    {
                    string_literal102=(Token)match(input,45,FOLLOW_45_in_invariantExpression1624); 
                    string_literal102_tree = 
                    (CommonTree)adaptor.create(string_literal102)
                    ;
                    adaptor.addChild(root_0, string_literal102_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_invariantExpression1628); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal103=(Token)match(input,29,FOLLOW_29_in_invariantExpression1632); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:358:1: preconditionExpression returns [TCondition value] : 'pre' ^e= expression ( 'fail' f= STRING )? ';' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:359:2: ( 'pre' ^e= expression ( 'fail' f= STRING )? ';' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:359:4: 'pre' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal104=(Token)match(input,60,FOLLOW_60_in_preconditionExpression1651); 
            string_literal104_tree = 
            (CommonTree)adaptor.create(string_literal104)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal104_tree, root_0);


            pushFollow(FOLLOW_expression_in_preconditionExpression1656);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:359:24: ( 'fail' f= STRING )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==45) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:359:25: 'fail' f= STRING
                    {
                    string_literal105=(Token)match(input,45,FOLLOW_45_in_preconditionExpression1659); 
                    string_literal105_tree = 
                    (CommonTree)adaptor.create(string_literal105)
                    ;
                    adaptor.addChild(root_0, string_literal105_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_preconditionExpression1663); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal106=(Token)match(input,29,FOLLOW_29_in_preconditionExpression1667); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:363:1: postconditionExpression returns [TCondition value] : 'post' ^e= expression ( 'fail' f= STRING )? ';' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:364:2: ( 'post' ^e= expression ( 'fail' f= STRING )? ';' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:364:4: 'post' ^e= expression ( 'fail' f= STRING )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal107=(Token)match(input,59,FOLLOW_59_in_postconditionExpression1687); 
            string_literal107_tree = 
            (CommonTree)adaptor.create(string_literal107)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal107_tree, root_0);


            pushFollow(FOLLOW_expression_in_postconditionExpression1692);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:364:25: ( 'fail' f= STRING )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==45) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:364:26: 'fail' f= STRING
                    {
                    string_literal108=(Token)match(input,45,FOLLOW_45_in_postconditionExpression1695); 
                    string_literal108_tree = 
                    (CommonTree)adaptor.create(string_literal108)
                    ;
                    adaptor.addChild(root_0, string_literal108_tree);


                    f=(Token)match(input,STRING,FOLLOW_STRING_in_postconditionExpression1699); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                    }
                    break;

            }


            char_literal109=(Token)match(input,29,FOLLOW_29_in_postconditionExpression1703); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:371:1: expression returns [TExpression value, TIOperator opor] : andExpression ( '||' ^ andExpressionbis )* ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:381:2: ( andExpression ( '||' ^ andExpressionbis )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:381:4: andExpression ( '||' ^ andExpressionbis )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andExpression_in_expression1734);
            andExpression110=andExpression();

            state._fsp--;

            adaptor.addChild(root_0, andExpression110.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:382:3: ( '||' ^ andExpressionbis )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==84) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:382:4: '||' ^ andExpressionbis
            	    {
            	    string_literal111=(Token)match(input,84,FOLLOW_84_in_expression1740); 
            	    string_literal111_tree = 
            	    (CommonTree)adaptor.create(string_literal111)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal111_tree, root_0);


            	    pushFollow(FOLLOW_andExpressionbis_in_expression1743);
            	    andExpressionbis112=andExpressionbis();

            	    state._fsp--;

            	    adaptor.addChild(root_0, andExpressionbis112.getTree());

            	     retval.opor.addOperand((andExpressionbis112!=null?andExpressionbis112.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop43;
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:385:1: andExpressionbis returns [TExpression value] : andExpression ;
    public final TamagoCDLParser.andExpressionbis_return andExpressionbis() throws RecognitionException {
        TamagoCDLParser.andExpressionbis_return retval = new TamagoCDLParser.andExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.andExpression_return andExpression113 =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:386:3: ( andExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:386:3: andExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andExpression_in_andExpressionbis1764);
            andExpression113=andExpression();

            state._fsp--;

            adaptor.addChild(root_0, andExpression113.getTree());

             retval.value = (andExpression113!=null?andExpression113.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:390:1: andExpression returns [TExpression value, TIOperator opand] : relQuantExpression ( '&&' ^ relQuantExpressionbis )* ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:400:2: ( relQuantExpression ( '&&' ^ relQuantExpressionbis )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:400:4: relQuantExpression ( '&&' ^ relQuantExpressionbis )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_relQuantExpression_in_andExpression1790);
            relQuantExpression114=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression114.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:401:3: ( '&&' ^ relQuantExpressionbis )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==21) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:401:4: '&&' ^ relQuantExpressionbis
            	    {
            	    string_literal115=(Token)match(input,21,FOLLOW_21_in_andExpression1796); 
            	    string_literal115_tree = 
            	    (CommonTree)adaptor.create(string_literal115)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal115_tree, root_0);


            	    pushFollow(FOLLOW_relQuantExpressionbis_in_andExpression1799);
            	    relQuantExpressionbis116=relQuantExpressionbis();

            	    state._fsp--;

            	    adaptor.addChild(root_0, relQuantExpressionbis116.getTree());

            	     retval.opand.addOperand((relQuantExpressionbis116!=null?relQuantExpressionbis116.value:null)); 

            	    }
            	    break;

            	default :
            	    break loop44;
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:405:1: relQuantExpressionbis returns [TExpression value] : relQuantExpression ;
    public final TamagoCDLParser.relQuantExpressionbis_return relQuantExpressionbis() throws RecognitionException {
        TamagoCDLParser.relQuantExpressionbis_return retval = new TamagoCDLParser.relQuantExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.relQuantExpression_return relQuantExpression117 =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:406:3: ( relQuantExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:406:3: relQuantExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_relQuantExpression_in_relQuantExpressionbis1822);
            relQuantExpression117=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression117.getTree());

             retval.value = (relQuantExpression117!=null?relQuantExpression117.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:408:1: relQuantExpression returns [TExpression value] : ( litBoolean | quantExpression | relExpression | notExpression );
    public final TamagoCDLParser.relQuantExpression_return relQuantExpression() throws RecognitionException {
        TamagoCDLParser.relQuantExpression_return retval = new TamagoCDLParser.relQuantExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.litBoolean_return litBoolean118 =null;

        TamagoCDLParser.quantExpression_return quantExpression119 =null;

        TamagoCDLParser.relExpression_return relExpression120 =null;

        TamagoCDLParser.notExpression_return notExpression121 =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:409:2: ( litBoolean | quantExpression | relExpression | notExpression )
            int alt45=4;
            switch ( input.LA(1) ) {
            case 46:
            case 77:
                {
                alt45=1;
                }
                break;
            case QUANTIFIER:
                {
                alt45=2;
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
                alt45=3;
                }
                break;
            case NOTOPERATOR:
                {
                alt45=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }

            switch (alt45) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:410:4: litBoolean
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_litBoolean_in_relQuantExpression1841);
                    litBoolean118=litBoolean();

                    state._fsp--;

                    adaptor.addChild(root_0, litBoolean118.getTree());

                     retval.value =(litBoolean118!=null?litBoolean118.value:null); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:411:4: quantExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_quantExpression_in_relQuantExpression1848);
                    quantExpression119=quantExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, quantExpression119.getTree());

                     retval.value =(quantExpression119!=null?quantExpression119.value:null); 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:412:4: relExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_relExpression_in_relQuantExpression1855);
                    relExpression120=relExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, relExpression120.getTree());

                     retval.value =(relExpression120!=null?relExpression120.value:null); 

                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:413:4: notExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_notExpression_in_relQuantExpression1862);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:416:1: notExpression returns [TExpression value] : NOTOPERATOR relQuantExpression ;
    public final TamagoCDLParser.notExpression_return notExpression() throws RecognitionException {
        TamagoCDLParser.notExpression_return retval = new TamagoCDLParser.notExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NOTOPERATOR122=null;
        TamagoCDLParser.relQuantExpression_return relQuantExpression123 =null;


        CommonTree NOTOPERATOR122_tree=null;

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:417:2: ( NOTOPERATOR relQuantExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:417:4: NOTOPERATOR relQuantExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            NOTOPERATOR122=(Token)match(input,NOTOPERATOR,FOLLOW_NOTOPERATOR_in_notExpression1879); 
            NOTOPERATOR122_tree = 
            (CommonTree)adaptor.create(NOTOPERATOR122)
            ;
            adaptor.addChild(root_0, NOTOPERATOR122_tree);


            pushFollow(FOLLOW_relQuantExpression_in_notExpression1881);
            relQuantExpression123=relQuantExpression();

            state._fsp--;

            adaptor.addChild(root_0, relQuantExpression123.getTree());

             retval.value = new TINot((relQuantExpression123!=null?relQuantExpression123.value:null)); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:420:1: litBoolean returns [TBool value] : ( 'true' | 'false' );
    public final TamagoCDLParser.litBoolean_return litBoolean() throws RecognitionException {
        TamagoCDLParser.litBoolean_return retval = new TamagoCDLParser.litBoolean_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal124=null;
        Token string_literal125=null;

        CommonTree string_literal124_tree=null;
        CommonTree string_literal125_tree=null;

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:421:2: ( 'true' | 'false' )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==77) ) {
                alt46=1;
            }
            else if ( (LA46_0==46) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:421:4: 'true'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal124=(Token)match(input,77,FOLLOW_77_in_litBoolean1898); 
                    string_literal124_tree = 
                    (CommonTree)adaptor.create(string_literal124)
                    ;
                    adaptor.addChild(root_0, string_literal124_tree);


                     retval.value = new TIBool(true); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:422:4: 'false'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal125=(Token)match(input,46,FOLLOW_46_in_litBoolean1906); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:423:1: quantExpression returns [TExpression value] : quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:424:2: (quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:424:4: quant= QUANTIFIER var= ID ':' t= type dom= domainQuant '{' body= expression '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            quant=(Token)match(input,QUANTIFIER,FOLLOW_QUANTIFIER_in_quantExpression1922); 
            quant_tree = 
            (CommonTree)adaptor.create(quant)
            ;
            adaptor.addChild(root_0, quant_tree);


            var=(Token)match(input,ID,FOLLOW_ID_in_quantExpression1927); 
            var_tree = 
            (CommonTree)adaptor.create(var)
            ;
            adaptor.addChild(root_0, var_tree);


            char_literal126=(Token)match(input,28,FOLLOW_28_in_quantExpression1930); 
            char_literal126_tree = 
            (CommonTree)adaptor.create(char_literal126)
            ;
            adaptor.addChild(root_0, char_literal126_tree);


            pushFollow(FOLLOW_type_in_quantExpression1935);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            pushFollow(FOLLOW_domainQuant_in_quantExpression1942);
            dom=domainQuant();

            state._fsp--;

            adaptor.addChild(root_0, dom.getTree());

            char_literal127=(Token)match(input,83,FOLLOW_83_in_quantExpression1947); 
            char_literal127_tree = 
            (CommonTree)adaptor.create(char_literal127)
            ;
            adaptor.addChild(root_0, char_literal127_tree);


            pushFollow(FOLLOW_expression_in_quantExpression1953);
            body=expression();

            state._fsp--;

            adaptor.addChild(root_0, body.getTree());

            char_literal128=(Token)match(input,85,FOLLOW_85_in_quantExpression1956); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:438:1: domainQuant returns [String kind, Collection<Object> value] : ( 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression ) | 'from' d= expression 'to' e= expression );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:442:2: ( 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression ) | 'from' d= expression 'to' e= expression )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==50) ) {
                alt49=1;
            }
            else if ( (LA49_0==47) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:443:2: 'in' ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal129=(Token)match(input,50,FOLLOW_50_in_domainQuant1980); 
                    string_literal129_tree = 
                    (CommonTree)adaptor.create(string_literal129)
                    ;
                    adaptor.addChild(root_0, string_literal129_tree);


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:443:7: ( 'set' '[' a= expression ( ',' b= expression )* ']' | 'coll' c= expression )
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==69) ) {
                        alt48=1;
                    }
                    else if ( (LA48_0==41) ) {
                        alt48=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 48, 0, input);

                        throw nvae;

                    }
                    switch (alt48) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:443:9: 'set' '[' a= expression ( ',' b= expression )* ']'
                            {
                            string_literal130=(Token)match(input,69,FOLLOW_69_in_domainQuant1984); 
                            string_literal130_tree = 
                            (CommonTree)adaptor.create(string_literal130)
                            ;
                            adaptor.addChild(root_0, string_literal130_tree);


                            char_literal131=(Token)match(input,33,FOLLOW_33_in_domainQuant1986); 
                            char_literal131_tree = 
                            (CommonTree)adaptor.create(char_literal131)
                            ;
                            adaptor.addChild(root_0, char_literal131_tree);


                            pushFollow(FOLLOW_expression_in_domainQuant1990);
                            a=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, a.getTree());

                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:443:32: ( ',' b= expression )*
                            loop47:
                            do {
                                int alt47=2;
                                int LA47_0 = input.LA(1);

                                if ( (LA47_0==25) ) {
                                    alt47=1;
                                }


                                switch (alt47) {
                            	case 1 :
                            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:443:33: ',' b= expression
                            	    {
                            	    char_literal132=(Token)match(input,25,FOLLOW_25_in_domainQuant1993); 
                            	    char_literal132_tree = 
                            	    (CommonTree)adaptor.create(char_literal132)
                            	    ;
                            	    adaptor.addChild(root_0, char_literal132_tree);


                            	    pushFollow(FOLLOW_expression_in_domainQuant1997);
                            	    b=expression();

                            	    state._fsp--;

                            	    adaptor.addChild(root_0, b.getTree());

                            	    retval.value.add((b!=null?b.value:null));

                            	    }
                            	    break;

                            	default :
                            	    break loop47;
                                }
                            } while (true);


                            char_literal133=(Token)match(input,35,FOLLOW_35_in_domainQuant2004); 
                            char_literal133_tree = 
                            (CommonTree)adaptor.create(char_literal133)
                            ;
                            adaptor.addChild(root_0, char_literal133_tree);


                             retval.kind = "set"; retval.value.add((a!=null?a.value:null));  

                            }
                            break;
                        case 2 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:444:4: 'coll' c= expression
                            {
                            string_literal134=(Token)match(input,41,FOLLOW_41_in_domainQuant2011); 
                            string_literal134_tree = 
                            (CommonTree)adaptor.create(string_literal134)
                            ;
                            adaptor.addChild(root_0, string_literal134_tree);


                            pushFollow(FOLLOW_expression_in_domainQuant2015);
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
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:445:4: 'from' d= expression 'to' e= expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal135=(Token)match(input,47,FOLLOW_47_in_domainQuant2024); 
                    string_literal135_tree = 
                    (CommonTree)adaptor.create(string_literal135)
                    ;
                    adaptor.addChild(root_0, string_literal135_tree);


                    pushFollow(FOLLOW_expression_in_domainQuant2028);
                    d=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, d.getTree());

                    string_literal136=(Token)match(input,74,FOLLOW_74_in_domainQuant2030); 
                    string_literal136_tree = 
                    (CommonTree)adaptor.create(string_literal136)
                    ;
                    adaptor.addChild(root_0, string_literal136_tree);


                    pushFollow(FOLLOW_expression_in_domainQuant2034);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:448:1: relExpression returns [TExpression value, String op, ArrayList<TExpression> exprs ] : a= arithExpression ( RELOP ^b= arithExpression )? ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:455:2: (a= arithExpression ( RELOP ^b= arithExpression )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:455:4: a= arithExpression ( RELOP ^b= arithExpression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_arithExpression_in_relExpression2063);
            a=arithExpression();

            state._fsp--;

            adaptor.addChild(root_0, a.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:456:3: ( RELOP ^b= arithExpression )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==RELOP) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:456:4: RELOP ^b= arithExpression
                    {
                    RELOP137=(Token)match(input,RELOP,FOLLOW_RELOP_in_relExpression2069); 
                    RELOP137_tree = 
                    (CommonTree)adaptor.create(RELOP137)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(RELOP137_tree, root_0);


                    pushFollow(FOLLOW_arithExpression_in_relExpression2076);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:462:1: arithExpression returns [TExpression value, String op, ArrayList<TExpression> exprs] : multExpression ( ADDOP ^ multExpressionbis )? ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:469:2: ( multExpression ( ADDOP ^ multExpressionbis )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:469:4: multExpression ( ADDOP ^ multExpressionbis )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multExpression_in_arithExpression2113);
            multExpression138=multExpression();

            state._fsp--;

            adaptor.addChild(root_0, multExpression138.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:470:3: ( ADDOP ^ multExpressionbis )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==ADDOP) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:470:4: ADDOP ^ multExpressionbis
                    {
                    ADDOP139=(Token)match(input,ADDOP,FOLLOW_ADDOP_in_arithExpression2119); 
                    ADDOP139_tree = 
                    (CommonTree)adaptor.create(ADDOP139)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ADDOP139_tree, root_0);


                    pushFollow(FOLLOW_multExpressionbis_in_arithExpression2124);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:474:1: multExpressionbis returns [TExpression value] : multExpression ;
    public final TamagoCDLParser.multExpressionbis_return multExpressionbis() throws RecognitionException {
        TamagoCDLParser.multExpressionbis_return retval = new TamagoCDLParser.multExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.multExpression_return multExpression141 =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:475:3: ( multExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:475:3: multExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multExpression_in_multExpressionbis2146);
            multExpression141=multExpression();

            state._fsp--;

            adaptor.addChild(root_0, multExpression141.getTree());

             retval.value = (multExpression141!=null?multExpression141.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:477:1: multExpression returns [TExpression value, String op, ArrayList<TExpression> exprs] : unaryExpression ( MULTOP ^ unaryExpressionbis )? ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:484:2: ( unaryExpression ( MULTOP ^ unaryExpressionbis )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:484:4: unaryExpression ( MULTOP ^ unaryExpressionbis )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExpression_in_multExpression2171);
            unaryExpression142=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression142.getTree());

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:485:3: ( MULTOP ^ unaryExpressionbis )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==MULTOP) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:485:4: MULTOP ^ unaryExpressionbis
                    {
                    MULTOP143=(Token)match(input,MULTOP,FOLLOW_MULTOP_in_multExpression2176); 
                    MULTOP143_tree = 
                    (CommonTree)adaptor.create(MULTOP143)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MULTOP143_tree, root_0);


                    pushFollow(FOLLOW_unaryExpressionbis_in_multExpression2181);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:489:1: unaryExpressionbis returns [TExpression value] : unaryExpression ;
    public final TamagoCDLParser.unaryExpressionbis_return unaryExpressionbis() throws RecognitionException {
        TamagoCDLParser.unaryExpressionbis_return retval = new TamagoCDLParser.unaryExpressionbis_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.unaryExpression_return unaryExpression145 =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:490:2: ( unaryExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:491:2: unaryExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExpression_in_unaryExpressionbis2206);
            unaryExpression145=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression145.getTree());

             retval.value = (unaryExpression145!=null?unaryExpression145.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:493:1: unaryExpression returns [TExpression value] : b= primaryExpression ;
    public final TamagoCDLParser.unaryExpression_return unaryExpression() throws RecognitionException {
        TamagoCDLParser.unaryExpression_return retval = new TamagoCDLParser.unaryExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        TamagoCDLParser.primaryExpression_return b =null;



        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:494:2: (b= primaryExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:497:2: b= primaryExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_primaryExpression_in_unaryExpression2230);
            b=primaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, b.getTree());

            retval.value =(b!=null?b.value:null); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:499:1: minusArithExpression returns [TExpression value] : '~' a= arithExpression ;
    public final TamagoCDLParser.minusArithExpression_return minusArithExpression() throws RecognitionException {
        TamagoCDLParser.minusArithExpression_return retval = new TamagoCDLParser.minusArithExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal146=null;
        TamagoCDLParser.arithExpression_return a =null;


        CommonTree char_literal146_tree=null;

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:500:2: ( '~' a= arithExpression )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:500:4: '~' a= arithExpression
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal146=(Token)match(input,86,FOLLOW_86_in_minusArithExpression2246); 
            char_literal146_tree = 
            (CommonTree)adaptor.create(char_literal146)
            ;
            adaptor.addChild(root_0, char_literal146_tree);


            pushFollow(FOLLOW_arithExpression_in_minusArithExpression2250);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:516:1: primaryExpression returns [TExpression value] : ( '(' a= expression ')' |b= literalUntypedExpression |c= literalArithExpression |e= atomicExpression );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:517:2: ( '(' a= expression ')' |b= literalUntypedExpression |c= literalArithExpression |e= atomicExpression )
            int alt53=4;
            switch ( input.LA(1) ) {
            case 22:
                {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==ID) ) {
                    int LA53_5 = input.LA(3);

                    if ( (LA53_5==23) ) {
                        int LA53_7 = input.LA(4);

                        if ( (LA53_7==ID||LA53_7==20||LA53_7==22||LA53_7==31||LA53_7==73) ) {
                            alt53=4;
                        }
                        else if ( (LA53_7==EOF||LA53_7==ADDOP||LA53_7==MULTOP||LA53_7==RELOP||LA53_7==21||LA53_7==23||LA53_7==25||LA53_7==29||LA53_7==35||LA53_7==45||LA53_7==74||(LA53_7 >= 83 && LA53_7 <= 85)) ) {
                            alt53=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 53, 7, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA53_5==ADDOP||LA53_5==MULTOP||LA53_5==RELOP||(LA53_5 >= 21 && LA53_5 <= 22)||LA53_5==26||LA53_5==33||LA53_5==84) ) {
                        alt53=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 5, input);

                        throw nvae;

                    }
                }
                else if ( (LA53_1==FLOAT||LA53_1==INT||LA53_1==NOTOPERATOR||LA53_1==QUANTIFIER||LA53_1==STRING||LA53_1==20||LA53_1==22||LA53_1==31||LA53_1==46||(LA53_1 >= 56 && LA53_1 <= 57)||LA53_1==73||LA53_1==77) ) {
                    alt53=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;

                }
                }
                break;
            case 56:
            case 57:
                {
                alt53=2;
                }
                break;
            case FLOAT:
            case INT:
            case STRING:
                {
                alt53=3;
                }
                break;
            case ID:
            case 20:
            case 31:
            case 73:
                {
                alt53=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }

            switch (alt53) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:518:3: '(' a= expression ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal147=(Token)match(input,22,FOLLOW_22_in_primaryExpression2268); 
                    char_literal147_tree = 
                    (CommonTree)adaptor.create(char_literal147)
                    ;
                    adaptor.addChild(root_0, char_literal147_tree);


                    pushFollow(FOLLOW_expression_in_primaryExpression2272);
                    a=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    char_literal148=(Token)match(input,23,FOLLOW_23_in_primaryExpression2274); 
                    char_literal148_tree = 
                    (CommonTree)adaptor.create(char_literal148)
                    ;
                    adaptor.addChild(root_0, char_literal148_tree);


                    retval.value =(a!=null?a.value:null);

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:519:5: b= literalUntypedExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_literalUntypedExpression_in_primaryExpression2284);
                    b=literalUntypedExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                    retval.value =(b!=null?b.value:null); 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:520:5: c= literalArithExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_literalArithExpression_in_primaryExpression2294);
                    c=literalArithExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());

                     retval.value =(c!=null?c.value:null); 

                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:521:5: e= atomicExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atomicExpression_in_primaryExpression2304);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:524:1: literalUntypedExpression returns [TExpression value] : ( 'null' ^| 'nil' ^);
    public final TamagoCDLParser.literalUntypedExpression_return literalUntypedExpression() throws RecognitionException {
        TamagoCDLParser.literalUntypedExpression_return retval = new TamagoCDLParser.literalUntypedExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal149=null;
        Token string_literal150=null;

        CommonTree string_literal149_tree=null;
        CommonTree string_literal150_tree=null;

        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:525:2: ( 'null' ^| 'nil' ^)
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==57) ) {
                alt54=1;
            }
            else if ( (LA54_0==56) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }
            switch (alt54) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:525:4: 'null' ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal149=(Token)match(input,57,FOLLOW_57_in_literalUntypedExpression2322); 
                    string_literal149_tree = 
                    (CommonTree)adaptor.create(string_literal149)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal149_tree, root_0);


                     retval.value =new TINil(); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:525:37: 'nil' ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal150=(Token)match(input,56,FOLLOW_56_in_literalUntypedExpression2328); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:527:1: literalArithExpression returns [TExpression value] : (f= FLOAT |i= INT |s= STRING );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:528:2: (f= FLOAT |i= INT |s= STRING )
            int alt55=3;
            switch ( input.LA(1) ) {
            case FLOAT:
                {
                alt55=1;
                }
                break;
            case INT:
                {
                alt55=2;
                }
                break;
            case STRING:
                {
                alt55=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }

            switch (alt55) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:529:4: f= FLOAT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_literalArithExpression2351); 
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);


                     retval.value = new TIReal(Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:530:4: i= INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    i=(Token)match(input,INT,FOLLOW_INT_in_literalArithExpression2360); 
                    i_tree = 
                    (CommonTree)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);


                     retval.value = new TIInteger(Integer.parseInt((i!=null?i.getText():null))); 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:531:4: s= STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_literalArithExpression2370); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:534:1: atomicExpression returns [TExpression value, Triplet<TExpression, Collection<TExpression>, Boolean> d, String t] : ( '(' p= ID ')' e= atomicExpression | ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )? );
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:535:2: ( '(' p= ID ')' e= atomicExpression | ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )? )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==22) ) {
                alt59=1;
            }
            else if ( (LA59_0==ID||LA59_0==20||LA59_0==31||LA59_0==73) ) {
                alt59=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }
            switch (alt59) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:536:2: '(' p= ID ')' e= atomicExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal151=(Token)match(input,22,FOLLOW_22_in_atomicExpression2388); 
                    char_literal151_tree = 
                    (CommonTree)adaptor.create(char_literal151)
                    ;
                    adaptor.addChild(root_0, char_literal151_tree);


                    p=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2392); 
                    p_tree = 
                    (CommonTree)adaptor.create(p)
                    ;
                    adaptor.addChild(root_0, p_tree);


                    char_literal152=(Token)match(input,23,FOLLOW_23_in_atomicExpression2394); 
                    char_literal152_tree = 
                    (CommonTree)adaptor.create(char_literal152)
                    ;
                    adaptor.addChild(root_0, char_literal152_tree);


                    pushFollow(FOLLOW_atomicExpression_in_atomicExpression2398);
                    e=atomicExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                     retval.value = TamagoCDLEaseFactory.genCast((p!=null?p.getText():null),(e!=null?e.value:null)); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:538:2: ( '@return' | '#' v= ID | 'this' |x= ID ) ( identSuffix )? ( '.' ^a= atomicExpression )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:538:2: ( '@return' | '#' v= ID | 'this' |x= ID )
                    int alt56=4;
                    switch ( input.LA(1) ) {
                    case 31:
                        {
                        alt56=1;
                        }
                        break;
                    case 20:
                        {
                        alt56=2;
                        }
                        break;
                    case 73:
                        {
                        alt56=3;
                        }
                        break;
                    case ID:
                        {
                        alt56=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 56, 0, input);

                        throw nvae;

                    }

                    switch (alt56) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:538:5: '@return'
                            {
                            string_literal153=(Token)match(input,31,FOLLOW_31_in_atomicExpression2410); 
                            string_literal153_tree = 
                            (CommonTree)adaptor.create(string_literal153)
                            ;
                            adaptor.addChild(root_0, string_literal153_tree);


                             retval.t = "@return"; 

                            }
                            break;
                        case 2 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:539:4: '#' v= ID
                            {
                            char_literal154=(Token)match(input,20,FOLLOW_20_in_atomicExpression2417); 
                            char_literal154_tree = 
                            (CommonTree)adaptor.create(char_literal154)
                            ;
                            adaptor.addChild(root_0, char_literal154_tree);


                            v=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2421); 
                            v_tree = 
                            (CommonTree)adaptor.create(v)
                            ;
                            adaptor.addChild(root_0, v_tree);


                            retval.t = "#"+(v!=null?v.getText():null); 

                            }
                            break;
                        case 3 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:540:3: 'this'
                            {
                            string_literal155=(Token)match(input,73,FOLLOW_73_in_atomicExpression2427); 
                            string_literal155_tree = 
                            (CommonTree)adaptor.create(string_literal155)
                            ;
                            adaptor.addChild(root_0, string_literal155_tree);


                             retval.t = "this"; 

                            }
                            break;
                        case 4 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:541:4: x= ID
                            {
                            x=(Token)match(input,ID,FOLLOW_ID_in_atomicExpression2436); 
                            x_tree = 
                            (CommonTree)adaptor.create(x)
                            ;
                            adaptor.addChild(root_0, x_tree);


                             retval.t = (x!=null?x.getText():null); 

                            }
                            break;

                    }


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:543:2: ( identSuffix )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==22||LA57_0==33) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:543:3: identSuffix
                            {
                            pushFollow(FOLLOW_identSuffix_in_atomicExpression2445);
                            identSuffix156=identSuffix();

                            state._fsp--;

                            adaptor.addChild(root_0, identSuffix156.getTree());

                            retval.d =(identSuffix156!=null?identSuffix156.value:null);

                            }
                            break;

                    }


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:544:2: ( '.' ^a= atomicExpression )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==26) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:544:3: '.' ^a= atomicExpression
                            {
                            char_literal157=(Token)match(input,26,FOLLOW_26_in_atomicExpression2453); 
                            char_literal157_tree = 
                            (CommonTree)adaptor.create(char_literal157)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(char_literal157_tree, root_0);


                            pushFollow(FOLLOW_atomicExpression_in_atomicExpression2458);
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:548:1: identSuffix returns [ tamagocc.util.Triplet<TExpression,Collection<TExpression>,Boolean> value] : (a= arrayIndexExpression |b= arguments ) ( '@pre' )? ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:552:2: ( (a= arrayIndexExpression |b= arguments ) ( '@pre' )? )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:552:4: (a= arrayIndexExpression |b= arguments ) ( '@pre' )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:552:4: (a= arrayIndexExpression |b= arguments )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==33) ) {
                alt60=1;
            }
            else if ( (LA60_0==22) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }
            switch (alt60) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:552:6: a= arrayIndexExpression
                    {
                    pushFollow(FOLLOW_arrayIndexExpression_in_identSuffix2490);
                    a=arrayIndexExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, a.getTree());

                    retval.value.setL((a!=null?a.value:null));

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:553:5: b= arguments
                    {
                    pushFollow(FOLLOW_arguments_in_identSuffix2500);
                    b=arguments();

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());

                     retval.value.setC((b!=null?b.value:null)) ; 

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:554:2: ( '@pre' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==30) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:554:3: '@pre'
                    {
                    string_literal158=(Token)match(input,30,FOLLOW_30_in_identSuffix2516); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
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
    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:558:1: arrayIndexExpression returns [TExpression value] : '[' i= arithExpression ']' ;
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
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:2: ( '[' i= arithExpression ']' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:4: '[' i= arithExpression ']'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal159=(Token)match(input,33,FOLLOW_33_in_arrayIndexExpression2539); 
            char_literal159_tree = 
            (CommonTree)adaptor.create(char_literal159)
            ;
            adaptor.addChild(root_0, char_literal159_tree);


            pushFollow(FOLLOW_arithExpression_in_arrayIndexExpression2543);
            i=arithExpression();

            state._fsp--;

            adaptor.addChild(root_0, i.getTree());

            char_literal160=(Token)match(input,35,FOLLOW_35_in_arrayIndexExpression2545); 
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

            catch(RecognitionException regexception) {
                throw new RuntimeException(regexception);
           }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayIndexExpression"

    // Delegated rules


 

    public static final BitSet FOLLOW_moduleDeclaration_in_tamagoEntity89 = new BitSet(new long[]{0x0000040000000000L,0x0000000000004010L});
    public static final BitSet FOLLOW_usingDeclaration_in_tamagoEntity95 = new BitSet(new long[]{0x0000040000000000L,0x0000000000004010L});
    public static final BitSet FOLLOW_serviceEntity_in_tamagoEntity105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_componentEntity_in_tamagoEntity114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_moduleDeclaration138 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_moduleDeclaration143 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_moduleDeclaration145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedName170 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_qualifiedName173 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_qualifiedName178 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedNameWithWildCard209 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_26_in_qualifiedNameWithWildCard212 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_qualifiedNameWithWildCard216 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_27_in_qualifiedNameWithWildCard226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_usingDeclaration250 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedNameWithWildCard_in_usingDeclaration255 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_usingDeclaration257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_percolator274 = new BitSet(new long[]{0x0000000001000400L});
    public static final BitSet FOLLOW_24_in_percolator278 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_ID_in_percolator284 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_percolator289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_require304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_require307 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_require312 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_require314 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_require319 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_require321 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_require326 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_require328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_provide345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_provide348 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_provide353 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_provide355 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_provide360 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_provide362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_serviceEntity387 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_serviceEntity392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_serviceEntity394 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_implementsInterface_in_serviceEntity405 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_refineService_in_serviceEntity415 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_includeService_in_serviceEntity425 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_propertyDeclaration_in_serviceEntity435 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_invariantExpression_in_serviceEntity445 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_methodDeclaration_in_serviceEntity456 = new BitSet(new long[]{0x206A004000000000L,0x0000000000200004L});
    public static final BitSet FOLLOW_behaviorDeclaration_in_serviceEntity473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_serviceEntity481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_implementsInterface_in_listimplements508 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_refineService_in_listrefine536 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_includeService_in_listinclude562 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_percolator_in_listpercolators596 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_listproperties621 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_provide_in_listprovides647 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_require_in_listrequires671 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_invariantExpression_in_listinvariants695 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_listmethods721 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_42_in_componentEntity748 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_componentEntity753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_componentEntity755 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_percolator_in_componentEntity762 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_implementsInterface_in_componentEntity770 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_require_in_componentEntity778 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_provide_in_componentEntity786 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_propertyDeclaration_in_componentEntity794 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_invariantExpression_in_componentEntity802 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_methodDeclaration_in_componentEntity810 = new BitSet(new long[]{0x6462004000000000L,0x0000000000200008L});
    public static final BitSet FOLLOW_behaviorDeclaration_in_componentEntity819 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_componentEntity825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_implementsInterface843 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_implementsInterface848 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_implementsInterface850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_refineService868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_refineService871 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_refineService876 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_refineService878 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_refineService883 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_refineService885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_refineService899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_refineService902 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_refineService905 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_refineService907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_includeService924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_includeService927 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_includeService932 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_includeService934 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_includeService939 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_includeService941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_includeService955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_includeService958 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_qualifiedName_in_includeService961 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_includeService963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_propertyDeclaration980 = new BitSet(new long[]{0x8000000000000000L,0x0000000000040001L});
    public static final BitSet FOLLOW_accessProperty_in_propertyDeclaration985 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_propertyDeclaration989 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration993 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_propertyDeclaration995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_accessProperty1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_accessProperty1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_accessProperty1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_type1075 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_qualifiedName_in_type1082 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_type1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_primitiveType1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_primitiveType1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_primitiveType1132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_primitiveType1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_primitiveType1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_primitiveType1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_primitiveType1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_primitiveType1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_methodDeclaration1198 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_methodDeclaration1203 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration1207 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_parameters_in_methodDeclaration1211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_methodDeclaration1213 = new BitSet(new long[]{0x1801000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_48_in_methodDeclaration1220 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration1225 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_methodDeclaration1227 = new BitSet(new long[]{0x1800000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_preconditionExpression_in_methodDeclaration1238 = new BitSet(new long[]{0x0800000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_postconditionExpression_in_methodDeclaration1248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_methodDeclaration1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_parameters1277 = new BitSet(new long[]{0x0010118100800400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_parameters1282 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_parameters1286 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_25_in_parameters1289 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_parameters1293 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_parameters1297 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_parameters1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_arguments1350 = new BitSet(new long[]{0x0300400080D2AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_arguments1355 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_25_in_arguments1363 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_arguments1367 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_arguments1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_behaviorDeclaration1396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1399 = new BitSet(new long[]{0x0000080000000000L,0x0000000000201080L});
    public static final BitSet FOLLOW_43_in_behaviorDeclaration1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_behaviorDeclaration1407 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_behaviorDeclaration1412 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_behaviorDeclaration1414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000201080L});
    public static final BitSet FOLLOW_71_in_behaviorDeclaration1423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200040L});
    public static final BitSet FOLLOW_stateDeclaration_in_behaviorDeclaration1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200040L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000201000L});
    public static final BitSet FOLLOW_76_in_behaviorDeclaration1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_behaviorDeclaration1451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200800L});
    public static final BitSet FOLLOW_transitionDeclaration_in_behaviorDeclaration1458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200800L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_behaviorDeclaration1470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_stateDeclaration1494 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_stateDeclaration1499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_stateDeclaration1501 = new BitSet(new long[]{0x0000001000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_allowDeclaration_in_stateDeclaration1507 = new BitSet(new long[]{0x0000001000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_stateDeclaration1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_allowDeclaration1534 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_allowDeclaration1539 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_allowDeclaration1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_transitionDeclaration1560 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_transitionDeclaration1563 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_transitionDeclaration1570 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_transitionDeclaration1577 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transitionDeclaration1582 = new BitSet(new long[]{0x0000000020000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_transitionDeclaration1585 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_transitionDeclaration1590 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_transitionDeclaration1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_invariantExpression1616 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_invariantExpression1621 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_invariantExpression1624 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_invariantExpression1628 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_invariantExpression1632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_preconditionExpression1651 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_preconditionExpression1656 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_preconditionExpression1659 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_preconditionExpression1663 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_preconditionExpression1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_postconditionExpression1687 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_postconditionExpression1692 = new BitSet(new long[]{0x0000200020000000L});
    public static final BitSet FOLLOW_45_in_postconditionExpression1695 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_STRING_in_postconditionExpression1699 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_postconditionExpression1703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_expression1734 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_expression1740 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_andExpressionbis_in_expression1743 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_andExpression_in_andExpressionbis1764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relQuantExpression_in_andExpression1790 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_andExpression1796 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_relQuantExpressionbis_in_andExpression1799 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_relQuantExpression_in_relQuantExpressionbis1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_litBoolean_in_relQuantExpression1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantExpression_in_relQuantExpression1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relExpression_in_relQuantExpression1855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notExpression_in_relQuantExpression1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTOPERATOR_in_notExpression1879 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_relQuantExpression_in_notExpression1881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_litBoolean1898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_litBoolean1906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUANTIFIER_in_quantExpression1922 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_quantExpression1927 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_quantExpression1930 = new BitSet(new long[]{0x0010118100000400L,0x0000000000008102L});
    public static final BitSet FOLLOW_type_in_quantExpression1935 = new BitSet(new long[]{0x0004800000000000L});
    public static final BitSet FOLLOW_domainQuant_in_quantExpression1942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_quantExpression1947 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_quantExpression1953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_quantExpression1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_domainQuant1980 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_domainQuant1984 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_domainQuant1986 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1990 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_domainQuant1993 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant1997 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_domainQuant2004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_domainQuant2011 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant2015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_domainQuant2024 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant2028 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_domainQuant2030 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_domainQuant2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arithExpression_in_relExpression2063 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_RELOP_in_relExpression2069 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_relExpression2076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_arithExpression2113 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADDOP_in_arithExpression2119 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_multExpressionbis_in_arithExpression2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_multExpressionbis2146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multExpression2171 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_MULTOP_in_multExpression2176 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_unaryExpressionbis_in_multExpression2181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionbis2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_unaryExpression2230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_minusArithExpression2246 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_minusArithExpression2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_primaryExpression2268 = new BitSet(new long[]{0x030040008052AD00L,0x0000000000002200L});
    public static final BitSet FOLLOW_expression_in_primaryExpression2272 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_primaryExpression2274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalUntypedExpression_in_primaryExpression2284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalArithExpression_in_primaryExpression2294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicExpression_in_primaryExpression2304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_literalUntypedExpression2322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_literalUntypedExpression2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_literalArithExpression2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_literalArithExpression2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literalArithExpression2370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_atomicExpression2388 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2392 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_atomicExpression2394 = new BitSet(new long[]{0x0000000080500400L,0x0000000000000200L});
    public static final BitSet FOLLOW_atomicExpression_in_atomicExpression2398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_atomicExpression2410 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_20_in_atomicExpression2417 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2421 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_73_in_atomicExpression2427 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_ID_in_atomicExpression2436 = new BitSet(new long[]{0x0000000204400002L});
    public static final BitSet FOLLOW_identSuffix_in_atomicExpression2445 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_atomicExpression2453 = new BitSet(new long[]{0x0000000080500400L,0x0000000000000200L});
    public static final BitSet FOLLOW_atomicExpression_in_atomicExpression2458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayIndexExpression_in_identSuffix2490 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_arguments_in_identSuffix2500 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_identSuffix2516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_arrayIndexExpression2539 = new BitSet(new long[]{0x0300000080520D00L,0x0000000000000200L});
    public static final BitSet FOLLOW_arithExpression_in_arrayIndexExpression2543 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_arrayIndexExpression2545 = new BitSet(new long[]{0x0000000000000002L});

}