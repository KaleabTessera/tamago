package fr.lacl.tamago.aca.parser;
// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g 2011-03-16 17:22:42

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class convertACACDLLexer extends Lexer {
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

    public convertACACDLLexer() {;} 
    public convertACACDLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public convertACACDLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g"; }

    // $ANTLR start "WILDCARD"
    public final void mWILDCARD() throws RecognitionException {
        try {
            int _type = WILDCARD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:82:2: ( '_' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:82:4: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WILDCARD"

    // $ANTLR start "TABL"
    public final void mTABL() throws RecognitionException {
        try {
            int _type = TABL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:85:2: ( '&' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:85:4: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TABL"

    // $ANTLR start "LIST"
    public final void mLIST() throws RecognitionException {
        try {
            int _type = LIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:89:2: ( '|' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:89:4: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LIST"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:92:2: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' )* )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:92:4: ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' )*
            {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:92:4: ( 'a' .. 'z' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:92:5: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:92:15: ( 'a' .. 'z' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "DEFI"
    public final void mDEFI() throws RecognitionException {
        try {
            int _type = DEFI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:96:2: ( '==' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:96:4: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFI"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:100:2: ( ';' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:100:4: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:104:2: ( '!' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:104:4: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:108:2: ( '<' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:108:4: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "RT"
    public final void mRT() throws RecognitionException {
        try {
            int _type = RT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:112:2: ( '>' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:112:4: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RT"

    // $ANTLR start "SOD"
    public final void mSOD() throws RecognitionException {
        try {
            int _type = SOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:116:2: ( 'SOD' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:116:3: 'SOD'
            {
            match("SOD"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOD"

    // $ANTLR start "OBLI"
    public final void mOBLI() throws RecognitionException {
        try {
            int _type = OBLI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:120:2: ( 'OBL' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:120:4: 'OBL'
            {
            match("OBL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OBLI"

    // $ANTLR start "LP"
    public final void mLP() throws RecognitionException {
        try {
            int _type = LP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:124:2: ( '(' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:124:4: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LP"

    // $ANTLR start "RP"
    public final void mRP() throws RecognitionException {
        try {
            int _type = RP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:128:2: ( ')' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:128:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RP"

    // $ANTLR start "COMA"
    public final void mCOMA() throws RecognitionException {
        try {
            int _type = COMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:132:2: ( ',' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:132:4: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMA"

    // $ANTLR start "PA"
    public final void mPA() throws RecognitionException {
        try {
            int _type = PA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:136:2: ( '||' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:136:4: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PA"

    // $ANTLR start "IL"
    public final void mIL() throws RecognitionException {
        try {
            int _type = IL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:140:2: ( '|||' )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:140:4: '|||'
            {
            match("|||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IL"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:144:2: ( ( ' ' | '\\n' )+ )
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:144:4: ( ' ' | '\\n' )+
            {
            // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:144:4: ( ' ' | '\\n' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\n'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:
            	    {
            	    if ( input.LA(1)=='\n'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:8: ( WILDCARD | TABL | LIST | STRING | DEFI | SEMI | NOT | LT | RT | SOD | OBLI | LP | RP | COMA | PA | IL | WS )
        int alt3=17;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:10: WILDCARD
                {
                mWILDCARD(); 

                }
                break;
            case 2 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:19: TABL
                {
                mTABL(); 

                }
                break;
            case 3 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:24: LIST
                {
                mLIST(); 

                }
                break;
            case 4 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:29: STRING
                {
                mSTRING(); 

                }
                break;
            case 5 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:36: DEFI
                {
                mDEFI(); 

                }
                break;
            case 6 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:41: SEMI
                {
                mSEMI(); 

                }
                break;
            case 7 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:46: NOT
                {
                mNOT(); 

                }
                break;
            case 8 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:50: LT
                {
                mLT(); 

                }
                break;
            case 9 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:53: RT
                {
                mRT(); 

                }
                break;
            case 10 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:56: SOD
                {
                mSOD(); 

                }
                break;
            case 11 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:60: OBLI
                {
                mOBLI(); 

                }
                break;
            case 12 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:65: LP
                {
                mLP(); 

                }
                break;
            case 13 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:68: RP
                {
                mRP(); 

                }
                break;
            case 14 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:71: COMA
                {
                mCOMA(); 

                }
                break;
            case 15 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:76: PA
                {
                mPA(); 

                }
                break;
            case 16 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:79: IL
                {
                mIL(); 

                }
                break;
            case 17 :
                // /Users/plouf/Dropbox/boulot/antlr/convertACACDL/convertACACDL.g:1:82: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\3\uffff\1\21\14\uffff\1\23\3\uffff";
    static final String DFA3_eofS =
        "\24\uffff";
    static final String DFA3_minS =
        "\1\12\2\uffff\1\174\14\uffff\1\174\3\uffff";
    static final String DFA3_maxS =
        "\1\174\2\uffff\1\174\14\uffff\1\174\3\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\21\1\uffff\1\3\1\20\1\17";
    static final String DFA3_specialS =
        "\24\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\17\25\uffff\1\17\1\7\4\uffff\1\2\1\uffff\1\14\1\15\2\uffff"+
            "\1\16\16\uffff\1\6\1\10\1\5\1\11\20\uffff\1\13\3\uffff\1\12"+
            "\13\uffff\1\1\1\uffff\32\4\1\uffff\1\3",
            "",
            "",
            "\1\20",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\22",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( WILDCARD | TABL | LIST | STRING | DEFI | SEMI | NOT | LT | RT | SOD | OBLI | LP | RP | COMA | PA | IL | WS );";
        }
    }
 

}