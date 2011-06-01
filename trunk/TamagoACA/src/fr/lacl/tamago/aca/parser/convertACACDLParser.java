package fr.lacl.tamago.aca.parser;
// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g 2011-03-16 17:22:42

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class convertACACDLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "STRING", "LP", "RP", "DEFI", "TABL", "LIST", "IL", "PA", "LT", "COMA", "RT", "SOD", "OBLI", "WILDCARD", "NOT", "WS"
    };
    public static final int EOF=-1;
    public static final int SEMI=4;
    public static final int STRING=5;
    public static final int LP=6;
    public static final int RP=7;
    public static final int DEFI=8;
    public static final int TABL=9;
    public static final int LIST=10;
    public static final int IL=11;
    public static final int PA=12;
    public static final int LT=13;
    public static final int COMA=14;
    public static final int RT=15;
    public static final int SOD=16;
    public static final int OBLI=17;
    public static final int WILDCARD=18;
    public static final int NOT=19;
    public static final int WS=20;

    // delegates
    // delegators


        public convertACACDLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public convertACACDLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return convertACACDLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g"; }


    public static class listeProcessus_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "listeProcessus"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:7:1: listeProcessus : declProcessus ( SEMI declProcessus )* SEMI ;
    public final convertACACDLParser.listeProcessus_return listeProcessus() throws RecognitionException {
        convertACACDLParser.listeProcessus_return retval = new convertACACDLParser.listeProcessus_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMI2=null;
        Token SEMI4=null;
        convertACACDLParser.declProcessus_return declProcessus1 = null;

        convertACACDLParser.declProcessus_return declProcessus3 = null;


        Object SEMI2_tree=null;
        Object SEMI4_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:8:2: ( declProcessus ( SEMI declProcessus )* SEMI )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:8:4: declProcessus ( SEMI declProcessus )* SEMI
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_declProcessus_in_listeProcessus26);
            declProcessus1=declProcessus();

            state._fsp--;

            adaptor.addChild(root_0, declProcessus1.getTree());
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:8:18: ( SEMI declProcessus )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==SEMI) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==STRING) ) {
                        alt1=1;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:8:19: SEMI declProcessus
            	    {
            	    SEMI2=(Token)match(input,SEMI,FOLLOW_SEMI_in_listeProcessus29); 
            	    pushFollow(FOLLOW_declProcessus_in_listeProcessus32);
            	    declProcessus3=declProcessus();

            	    state._fsp--;

            	    adaptor.addChild(root_0, declProcessus3.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            SEMI4=(Token)match(input,SEMI,FOLLOW_SEMI_in_listeProcessus36); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "listeProcessus"

    public static class declProcessus_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declProcessus"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:11:1: declProcessus : STRING LP RP DEFI expression ;
    public final convertACACDLParser.declProcessus_return declProcessus() throws RecognitionException {
        convertACACDLParser.declProcessus_return retval = new convertACACDLParser.declProcessus_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING5=null;
        Token LP6=null;
        Token RP7=null;
        Token DEFI8=null;
        convertACACDLParser.expression_return expression9 = null;


        Object STRING5_tree=null;
        Object LP6_tree=null;
        Object RP7_tree=null;
        Object DEFI8_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:12:2: ( STRING LP RP DEFI expression )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:12:4: STRING LP RP DEFI expression
            {
            root_0 = (Object)adaptor.nil();

            STRING5=(Token)match(input,STRING,FOLLOW_STRING_in_declProcessus49); 
            STRING5_tree = (Object)adaptor.create(STRING5);
            root_0 = (Object)adaptor.becomeRoot(STRING5_tree, root_0);

            LP6=(Token)match(input,LP,FOLLOW_LP_in_declProcessus52); 
            RP7=(Token)match(input,RP,FOLLOW_RP_in_declProcessus55); 
            DEFI8=(Token)match(input,DEFI,FOLLOW_DEFI_in_declProcessus58); 
            pushFollow(FOLLOW_expression_in_declProcessus61);
            expression9=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression9.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declProcessus"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:15:1: expression : ( process | list | tableau );
    public final convertACACDLParser.expression_return expression() throws RecognitionException {
        convertACACDLParser.expression_return retval = new convertACACDLParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        convertACACDLParser.process_return process10 = null;

        convertACACDLParser.list_return list11 = null;

        convertACACDLParser.tableau_return tableau12 = null;



        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:16:2: ( process | list | tableau )
            int alt2=3;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:16:4: process
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_process_in_expression75);
                    process10=process();

                    state._fsp--;

                    adaptor.addChild(root_0, process10.getTree());

                    }
                    break;
                case 2 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:17:4: list
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_list_in_expression80);
                    list11=list();

                    state._fsp--;

                    adaptor.addChild(root_0, list11.getTree());

                    }
                    break;
                case 3 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:18:4: tableau
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_tableau_in_expression85);
                    tableau12=tableau();

                    state._fsp--;

                    adaptor.addChild(root_0, tableau12.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class tableau_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tableau"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:21:1: tableau : triplet ( TABL triplet )* ;
    public final convertACACDLParser.tableau_return tableau() throws RecognitionException {
        convertACACDLParser.tableau_return retval = new convertACACDLParser.tableau_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TABL14=null;
        convertACACDLParser.triplet_return triplet13 = null;

        convertACACDLParser.triplet_return triplet15 = null;


        Object TABL14_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:22:2: ( triplet ( TABL triplet )* )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:22:4: triplet ( TABL triplet )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_triplet_in_tableau96);
            triplet13=triplet();

            state._fsp--;

            adaptor.addChild(root_0, triplet13.getTree());
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:22:12: ( TABL triplet )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==TABL) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:22:13: TABL triplet
            	    {
            	    TABL14=(Token)match(input,TABL,FOLLOW_TABL_in_tableau99); 
            	    TABL14_tree = (Object)adaptor.create(TABL14);
            	    root_0 = (Object)adaptor.becomeRoot(TABL14_tree, root_0);

            	    pushFollow(FOLLOW_triplet_in_tableau102);
            	    triplet15=triplet();

            	    state._fsp--;

            	    adaptor.addChild(root_0, triplet15.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tableau"

    public static class list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:25:1: list : action ( LIST action )* ;
    public final convertACACDLParser.list_return list() throws RecognitionException {
        convertACACDLParser.list_return retval = new convertACACDLParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LIST17=null;
        convertACACDLParser.action_return action16 = null;

        convertACACDLParser.action_return action18 = null;


        Object LIST17_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:26:2: ( action ( LIST action )* )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:26:4: action ( LIST action )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_action_in_list115);
            action16=action();

            state._fsp--;

            adaptor.addChild(root_0, action16.getTree());
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:26:11: ( LIST action )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==LIST) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:26:12: LIST action
            	    {
            	    LIST17=(Token)match(input,LIST,FOLLOW_LIST_in_list118); 
            	    LIST17_tree = (Object)adaptor.create(LIST17);
            	    root_0 = (Object)adaptor.becomeRoot(LIST17_tree, root_0);

            	    pushFollow(FOLLOW_action_in_list121);
            	    action18=action();

            	    state._fsp--;

            	    adaptor.addChild(root_0, action18.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list"

    public static class process_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "process"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:29:1: process : ( LP process ( ( IL | PA ) process )* RP | operation );
    public final convertACACDLParser.process_return process() throws RecognitionException {
        convertACACDLParser.process_return retval = new convertACACDLParser.process_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LP19=null;
        Token IL21=null;
        Token PA22=null;
        Token RP24=null;
        convertACACDLParser.process_return process20 = null;

        convertACACDLParser.process_return process23 = null;

        convertACACDLParser.operation_return operation25 = null;


        Object LP19_tree=null;
        Object IL21_tree=null;
        Object PA22_tree=null;
        Object RP24_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:2: ( LP process ( ( IL | PA ) process )* RP | operation )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LP) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=SOD && LA7_0<=OBLI)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:4: LP process ( ( IL | PA ) process )* RP
                    {
                    root_0 = (Object)adaptor.nil();

                    LP19=(Token)match(input,LP,FOLLOW_LP_in_process134); 
                    pushFollow(FOLLOW_process_in_process137);
                    process20=process();

                    state._fsp--;

                    adaptor.addChild(root_0, process20.getTree());
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:16: ( ( IL | PA ) process )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>=IL && LA6_0<=PA)) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:17: ( IL | PA ) process
                    	    {
                    	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:17: ( IL | PA )
                    	    int alt5=2;
                    	    int LA5_0 = input.LA(1);

                    	    if ( (LA5_0==IL) ) {
                    	        alt5=1;
                    	    }
                    	    else if ( (LA5_0==PA) ) {
                    	        alt5=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 5, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt5) {
                    	        case 1 :
                    	            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:18: IL
                    	            {
                    	            IL21=(Token)match(input,IL,FOLLOW_IL_in_process141); 
                    	            IL21_tree = (Object)adaptor.create(IL21);
                    	            root_0 = (Object)adaptor.becomeRoot(IL21_tree, root_0);


                    	            }
                    	            break;
                    	        case 2 :
                    	            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:30:22: PA
                    	            {
                    	            PA22=(Token)match(input,PA,FOLLOW_PA_in_process144); 
                    	            PA22_tree = (Object)adaptor.create(PA22);
                    	            root_0 = (Object)adaptor.becomeRoot(PA22_tree, root_0);


                    	            }
                    	            break;

                    	    }

                    	    pushFollow(FOLLOW_process_in_process148);
                    	    process23=process();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, process23.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    RP24=(Token)match(input,RP,FOLLOW_RP_in_process152); 

                    }
                    break;
                case 2 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:31:4: operation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_operation_in_process158);
                    operation25=operation();

                    state._fsp--;

                    adaptor.addChild(root_0, operation25.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "process"

    public static class triplet_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "triplet"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:40:1: triplet : LT token COMA token COMA token RT ;
    public final convertACACDLParser.triplet_return triplet() throws RecognitionException {
        convertACACDLParser.triplet_return retval = new convertACACDLParser.triplet_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LT26=null;
        Token COMA28=null;
        Token COMA30=null;
        Token RT32=null;
        convertACACDLParser.token_return token27 = null;

        convertACACDLParser.token_return token29 = null;

        convertACACDLParser.token_return token31 = null;


        Object LT26_tree=null;
        Object COMA28_tree=null;
        Object COMA30_tree=null;
        Object RT32_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:41:2: ( LT token COMA token COMA token RT )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:41:4: LT token COMA token COMA token RT
            {
            root_0 = (Object)adaptor.nil();

            LT26=(Token)match(input,LT,FOLLOW_LT_in_triplet177); 
            LT26_tree = (Object)adaptor.create(LT26);
            root_0 = (Object)adaptor.becomeRoot(LT26_tree, root_0);

            pushFollow(FOLLOW_token_in_triplet180);
            token27=token();

            state._fsp--;

            adaptor.addChild(root_0, token27.getTree());
            COMA28=(Token)match(input,COMA,FOLLOW_COMA_in_triplet182); 
            pushFollow(FOLLOW_token_in_triplet185);
            token29=token();

            state._fsp--;

            adaptor.addChild(root_0, token29.getTree());
            COMA30=(Token)match(input,COMA,FOLLOW_COMA_in_triplet187); 
            pushFollow(FOLLOW_token_in_triplet190);
            token31=token();

            state._fsp--;

            adaptor.addChild(root_0, token31.getTree());
            RT32=(Token)match(input,RT,FOLLOW_RT_in_triplet192); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "triplet"

    public static class operation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "operation"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:44:1: operation : ( sod | obligation );
    public final convertACACDLParser.operation_return operation() throws RecognitionException {
        convertACACDLParser.operation_return retval = new convertACACDLParser.operation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        convertACACDLParser.sod_return sod33 = null;

        convertACACDLParser.obligation_return obligation34 = null;



        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:45:2: ( sod | obligation )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==SOD) ) {
                alt8=1;
            }
            else if ( (LA8_0==OBLI) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:45:4: sod
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_sod_in_operation206);
                    sod33=sod();

                    state._fsp--;

                    adaptor.addChild(root_0, sod33.getTree());

                    }
                    break;
                case 2 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:46:4: obligation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_obligation_in_operation212);
                    obligation34=obligation();

                    state._fsp--;

                    adaptor.addChild(root_0, obligation34.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "operation"

    public static class sod_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sod"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:55:1: sod : SOD LP STRING COMA action COMA action RP ;
    public final convertACACDLParser.sod_return sod() throws RecognitionException {
        convertACACDLParser.sod_return retval = new convertACACDLParser.sod_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SOD35=null;
        Token LP36=null;
        Token STRING37=null;
        Token COMA38=null;
        Token COMA40=null;
        Token RP42=null;
        convertACACDLParser.action_return action39 = null;

        convertACACDLParser.action_return action41 = null;


        Object SOD35_tree=null;
        Object LP36_tree=null;
        Object STRING37_tree=null;
        Object COMA38_tree=null;
        Object COMA40_tree=null;
        Object RP42_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:56:2: ( SOD LP STRING COMA action COMA action RP )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:56:4: SOD LP STRING COMA action COMA action RP
            {
            root_0 = (Object)adaptor.nil();

            SOD35=(Token)match(input,SOD,FOLLOW_SOD_in_sod232); 
            SOD35_tree = (Object)adaptor.create(SOD35);
            root_0 = (Object)adaptor.becomeRoot(SOD35_tree, root_0);

            LP36=(Token)match(input,LP,FOLLOW_LP_in_sod235); 
            STRING37=(Token)match(input,STRING,FOLLOW_STRING_in_sod238); 
            STRING37_tree = (Object)adaptor.create(STRING37);
            adaptor.addChild(root_0, STRING37_tree);

            COMA38=(Token)match(input,COMA,FOLLOW_COMA_in_sod240); 
            pushFollow(FOLLOW_action_in_sod243);
            action39=action();

            state._fsp--;

            adaptor.addChild(root_0, action39.getTree());
            COMA40=(Token)match(input,COMA,FOLLOW_COMA_in_sod245); 
            pushFollow(FOLLOW_action_in_sod248);
            action41=action();

            state._fsp--;

            adaptor.addChild(root_0, action41.getTree());
            RP42=(Token)match(input,RP,FOLLOW_RP_in_sod250); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "sod"

    public static class obligation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "obligation"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:59:1: obligation : OBLI LP STRING COMA action COMA action RP ;
    public final convertACACDLParser.obligation_return obligation() throws RecognitionException {
        convertACACDLParser.obligation_return retval = new convertACACDLParser.obligation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OBLI43=null;
        Token LP44=null;
        Token STRING45=null;
        Token COMA46=null;
        Token COMA48=null;
        Token RP50=null;
        convertACACDLParser.action_return action47 = null;

        convertACACDLParser.action_return action49 = null;


        Object OBLI43_tree=null;
        Object LP44_tree=null;
        Object STRING45_tree=null;
        Object COMA46_tree=null;
        Object COMA48_tree=null;
        Object RP50_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:60:2: ( OBLI LP STRING COMA action COMA action RP )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:60:4: OBLI LP STRING COMA action COMA action RP
            {
            root_0 = (Object)adaptor.nil();

            OBLI43=(Token)match(input,OBLI,FOLLOW_OBLI_in_obligation262); 
            OBLI43_tree = (Object)adaptor.create(OBLI43);
            root_0 = (Object)adaptor.becomeRoot(OBLI43_tree, root_0);

            LP44=(Token)match(input,LP,FOLLOW_LP_in_obligation265); 
            STRING45=(Token)match(input,STRING,FOLLOW_STRING_in_obligation268); 
            STRING45_tree = (Object)adaptor.create(STRING45);
            adaptor.addChild(root_0, STRING45_tree);

            COMA46=(Token)match(input,COMA,FOLLOW_COMA_in_obligation270); 
            pushFollow(FOLLOW_action_in_obligation273);
            action47=action();

            state._fsp--;

            adaptor.addChild(root_0, action47.getTree());
            COMA48=(Token)match(input,COMA,FOLLOW_COMA_in_obligation275); 
            pushFollow(FOLLOW_action_in_obligation278);
            action49=action();

            state._fsp--;

            adaptor.addChild(root_0, action49.getTree());
            RP50=(Token)match(input,RP,FOLLOW_RP_in_obligation280); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "obligation"

    public static class action_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "action"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:63:1: action : LT envsec COMA evenement RT ;
    public final convertACACDLParser.action_return action() throws RecognitionException {
        convertACACDLParser.action_return retval = new convertACACDLParser.action_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LT51=null;
        Token COMA53=null;
        Token RT55=null;
        convertACACDLParser.envsec_return envsec52 = null;

        convertACACDLParser.evenement_return evenement54 = null;


        Object LT51_tree=null;
        Object COMA53_tree=null;
        Object RT55_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:64:2: ( LT envsec COMA evenement RT )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:64:4: LT envsec COMA evenement RT
            {
            root_0 = (Object)adaptor.nil();

            LT51=(Token)match(input,LT,FOLLOW_LT_in_action293); 
            LT51_tree = (Object)adaptor.create(LT51);
            root_0 = (Object)adaptor.becomeRoot(LT51_tree, root_0);

            pushFollow(FOLLOW_envsec_in_action296);
            envsec52=envsec();

            state._fsp--;

            adaptor.addChild(root_0, envsec52.getTree());
            COMA53=(Token)match(input,COMA,FOLLOW_COMA_in_action298); 
            pushFollow(FOLLOW_evenement_in_action301);
            evenement54=evenement();

            state._fsp--;

            adaptor.addChild(root_0, evenement54.getTree());
            RT55=(Token)match(input,RT,FOLLOW_RT_in_action303); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "action"

    public static class envsec_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "envsec"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:67:1: envsec : token COMA token COMA token COMA token ;
    public final convertACACDLParser.envsec_return envsec() throws RecognitionException {
        convertACACDLParser.envsec_return retval = new convertACACDLParser.envsec_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMA57=null;
        Token COMA59=null;
        Token COMA61=null;
        convertACACDLParser.token_return token56 = null;

        convertACACDLParser.token_return token58 = null;

        convertACACDLParser.token_return token60 = null;

        convertACACDLParser.token_return token62 = null;


        Object COMA57_tree=null;
        Object COMA59_tree=null;
        Object COMA61_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:68:2: ( token COMA token COMA token COMA token )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:68:4: token COMA token COMA token COMA token
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_token_in_envsec315);
            token56=token();

            state._fsp--;

            adaptor.addChild(root_0, token56.getTree());
            COMA57=(Token)match(input,COMA,FOLLOW_COMA_in_envsec317); 
            pushFollow(FOLLOW_token_in_envsec320);
            token58=token();

            state._fsp--;

            adaptor.addChild(root_0, token58.getTree());
            COMA59=(Token)match(input,COMA,FOLLOW_COMA_in_envsec322); 
            pushFollow(FOLLOW_token_in_envsec325);
            token60=token();

            state._fsp--;

            adaptor.addChild(root_0, token60.getTree());
            COMA61=(Token)match(input,COMA,FOLLOW_COMA_in_envsec327); 
            pushFollow(FOLLOW_token_in_envsec330);
            token62=token();

            state._fsp--;

            adaptor.addChild(root_0, token62.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "envsec"

    public static class evenement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "evenement"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:71:1: evenement : STRING LP ( STRING )? ( COMA STRING )* RP ;
    public final convertACACDLParser.evenement_return evenement() throws RecognitionException {
        convertACACDLParser.evenement_return retval = new convertACACDLParser.evenement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING63=null;
        Token LP64=null;
        Token STRING65=null;
        Token COMA66=null;
        Token STRING67=null;
        Token RP68=null;

        Object STRING63_tree=null;
        Object LP64_tree=null;
        Object STRING65_tree=null;
        Object COMA66_tree=null;
        Object STRING67_tree=null;
        Object RP68_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:2: ( STRING LP ( STRING )? ( COMA STRING )* RP )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:4: STRING LP ( STRING )? ( COMA STRING )* RP
            {
            root_0 = (Object)adaptor.nil();

            STRING63=(Token)match(input,STRING,FOLLOW_STRING_in_evenement342); 
            STRING63_tree = (Object)adaptor.create(STRING63);
            root_0 = (Object)adaptor.becomeRoot(STRING63_tree, root_0);

            LP64=(Token)match(input,LP,FOLLOW_LP_in_evenement345); 
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:16: ( STRING )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==STRING) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:16: STRING
                    {
                    STRING65=(Token)match(input,STRING,FOLLOW_STRING_in_evenement348); 
                    STRING65_tree = (Object)adaptor.create(STRING65);
                    adaptor.addChild(root_0, STRING65_tree);


                    }
                    break;

            }

            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:24: ( COMA STRING )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMA) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:72:25: COMA STRING
            	    {
            	    COMA66=(Token)match(input,COMA,FOLLOW_COMA_in_evenement352); 
            	    STRING67=(Token)match(input,STRING,FOLLOW_STRING_in_evenement355); 
            	    STRING67_tree = (Object)adaptor.create(STRING67);
            	    adaptor.addChild(root_0, STRING67_tree);


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            RP68=(Token)match(input,RP,FOLLOW_RP_in_evenement360); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "evenement"

    public static class token_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "token"
    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:75:1: token : ( WILDCARD | STRING | NOT STRING );
    public final convertACACDLParser.token_return token() throws RecognitionException {
        convertACACDLParser.token_return retval = new convertACACDLParser.token_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WILDCARD69=null;
        Token STRING70=null;
        Token NOT71=null;
        Token STRING72=null;

        Object WILDCARD69_tree=null;
        Object STRING70_tree=null;
        Object NOT71_tree=null;
        Object STRING72_tree=null;

        try {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:76:2: ( WILDCARD | STRING | NOT STRING )
            int alt11=3;
            switch ( input.LA(1) ) {
            case WILDCARD:
                {
                alt11=1;
                }
                break;
            case STRING:
                {
                alt11=2;
                }
                break;
            case NOT:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:76:4: WILDCARD
                    {
                    root_0 = (Object)adaptor.nil();

                    WILDCARD69=(Token)match(input,WILDCARD,FOLLOW_WILDCARD_in_token373); 
                    WILDCARD69_tree = (Object)adaptor.create(WILDCARD69);
                    adaptor.addChild(root_0, WILDCARD69_tree);


                    }
                    break;
                case 2 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:77:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    STRING70=(Token)match(input,STRING,FOLLOW_STRING_in_token379); 
                    STRING70_tree = (Object)adaptor.create(STRING70);
                    adaptor.addChild(root_0, STRING70_tree);


                    }
                    break;
                case 3 :
                    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:78:4: NOT STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT71=(Token)match(input,NOT,FOLLOW_NOT_in_token384); 
                    NOT71_tree = (Object)adaptor.create(NOT71);
                    root_0 = (Object)adaptor.becomeRoot(NOT71_tree, root_0);

                    STRING72=(Token)match(input,STRING,FOLLOW_STRING_in_token387); 
                    STRING72_tree = (Object)adaptor.create(STRING72);
                    adaptor.addChild(root_0, STRING72_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "token"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\23\uffff";
    static final String DFA2_eofS =
        "\23\uffff";
    static final String DFA2_minS =
        "\1\6\1\uffff\1\5\2\16\2\5\3\16\2\5\3\16\1\5\2\uffff\1\16";
    static final String DFA2_maxS =
        "\1\21\1\uffff\1\23\2\16\1\5\1\23\3\16\1\5\1\23\1\16\2\17\1\5\2\uffff"+
        "\1\17";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\16\uffff\1\2\1\3\1\uffff";
    static final String DFA2_specialS =
        "\23\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\6\uffff\1\2\2\uffff\2\1",
            "",
            "\1\4\14\uffff\1\3\1\5",
            "\1\6",
            "\1\6",
            "\1\7",
            "\1\11\14\uffff\1\10\1\12",
            "\1\6",
            "\1\13",
            "\1\13",
            "\1\14",
            "\1\16\14\uffff\1\15\1\17",
            "\1\13",
            "\1\20\1\21",
            "\1\20\1\21",
            "\1\22",
            "",
            "",
            "\1\20\1\21"
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "15:1: expression : ( process | list | tableau );";
        }
    }
 

    public static final BitSet FOLLOW_declProcessus_in_listeProcessus26 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_listeProcessus29 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_declProcessus_in_listeProcessus32 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_listeProcessus36 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_declProcessus49 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LP_in_declProcessus52 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RP_in_declProcessus55 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_DEFI_in_declProcessus58 = new BitSet(new long[]{0x0000000000032040L});
    public static final BitSet FOLLOW_expression_in_declProcessus61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_process_in_expression75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_expression80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableau_in_expression85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_triplet_in_tableau96 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_TABL_in_tableau99 = new BitSet(new long[]{0x0000000000032040L});
    public static final BitSet FOLLOW_triplet_in_tableau102 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_action_in_list115 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_LIST_in_list118 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_action_in_list121 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_LP_in_process134 = new BitSet(new long[]{0x0000000000030040L});
    public static final BitSet FOLLOW_process_in_process137 = new BitSet(new long[]{0x0000000000001880L});
    public static final BitSet FOLLOW_IL_in_process141 = new BitSet(new long[]{0x0000000000030040L});
    public static final BitSet FOLLOW_PA_in_process144 = new BitSet(new long[]{0x0000000000030040L});
    public static final BitSet FOLLOW_process_in_process148 = new BitSet(new long[]{0x0000000000001880L});
    public static final BitSet FOLLOW_RP_in_process152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operation_in_process158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_triplet177 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_triplet180 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_triplet182 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_triplet185 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_triplet187 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_triplet190 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RT_in_triplet192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sod_in_operation206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_obligation_in_operation212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOD_in_sod232 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LP_in_sod235 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_sod238 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_sod240 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_action_in_sod243 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_sod245 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_action_in_sod248 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RP_in_sod250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBLI_in_obligation262 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LP_in_obligation265 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_obligation268 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_obligation270 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_action_in_obligation273 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_obligation275 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_action_in_obligation278 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RP_in_obligation280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_action293 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_envsec_in_action296 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_action298 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_evenement_in_action301 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RT_in_action303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_token_in_envsec315 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_envsec317 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_envsec320 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_envsec322 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_envsec325 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMA_in_envsec327 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_token_in_envsec330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_evenement342 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LP_in_evenement345 = new BitSet(new long[]{0x00000000000040A0L});
    public static final BitSet FOLLOW_STRING_in_evenement348 = new BitSet(new long[]{0x0000000000004080L});
    public static final BitSet FOLLOW_COMA_in_evenement352 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_evenement355 = new BitSet(new long[]{0x0000000000004080L});
    public static final BitSet FOLLOW_RP_in_evenement360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WILDCARD_in_token373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_token379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_token384 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_token387 = new BitSet(new long[]{0x0000000000000002L});

}