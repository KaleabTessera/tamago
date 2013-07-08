// $ANTLR 3.4 /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g 2013-07-08 19:13:03

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

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TamagoCDLLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public TamagoCDLLexer() {} 
    public TamagoCDLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TamagoCDLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g"; }

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:12:7: ( '#' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:12:9: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:13:7: ( '&&' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:13:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:14:7: ( '(' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:14:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:15:7: ( ')' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:15:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:16:7: ( '*' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:16:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:17:7: ( ',' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:17:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:18:7: ( '.' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:18:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:19:7: ( '.*' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:19:9: '.*'
            {
            match(".*"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:20:7: ( ':' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:20:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:21:7: ( ';' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:21:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:22:7: ( '@pre' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:22:9: '@pre'
            {
            match("@pre"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:23:7: ( '@return' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:23:9: '@return'
            {
            match("@return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:24:7: ( 'String' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:24:9: 'String'
            {
            match("String"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:25:7: ( '[' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:25:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:26:7: ( '[]' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:26:9: '[]'
            {
            match("[]"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:27:7: ( ']' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:27:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:28:7: ( 'allow' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:28:9: 'allow'
            {
            match("allow"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:29:7: ( 'as' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:29:9: 'as'
            {
            match("as"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:30:7: ( 'behavior' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:30:9: 'behavior'
            {
            match("behavior"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:31:7: ( 'bool' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:31:9: 'bool'
            {
            match("bool"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:32:7: ( 'boolean' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:32:9: 'boolean'
            {
            match("boolean"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:33:7: ( 'coll' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:33:9: 'coll'
            {
            match("coll"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:34:7: ( 'component' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:34:9: 'component'
            {
            match("component"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:35:7: ( 'default' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:35:9: 'default'
            {
            match("default"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:36:7: ( 'double' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:36:9: 'double'
            {
            match("double"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:37:7: ( 'fail' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:37:9: 'fail'
            {
            match("fail"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:38:7: ( 'false' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:38:9: 'false'
            {
            match("false"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:39:7: ( 'from' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:39:9: 'from'
            {
            match("from"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:40:7: ( 'id' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:40:9: 'id'
            {
            match("id"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:41:7: ( 'implements' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:41:9: 'implements'
            {
            match("implements"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:42:7: ( 'in' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:42:9: 'in'
            {
            match("in"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:43:7: ( 'include' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:43:9: 'include'
            {
            match("include"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:44:7: ( 'int' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:44:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:45:7: ( 'invariant' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:45:9: 'invariant'
            {
            match("invariant"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:46:7: ( 'method' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:46:9: 'method'
            {
            match("method"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:47:7: ( 'module' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:47:9: 'module'
            {
            match("module"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:48:7: ( 'nil' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:48:9: 'nil'
            {
            match("nil"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:49:7: ( 'null' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:49:9: 'null'
            {
            match("null"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:50:7: ( 'percolator' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:50:9: 'percolator'
            {
            match("percolator"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:51:7: ( 'post' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:51:9: 'post'
            {
            match("post"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:52:7: ( 'pre' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:52:9: 'pre'
            {
            match("pre"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:53:7: ( 'property' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:53:9: 'property'
            {
            match("property"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:54:7: ( 'provide' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:54:9: 'provide'
            {
            match("provide"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:55:7: ( 'read' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:55:9: 'read'
            {
            match("read"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:56:7: ( 'readwrite' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:56:9: 'readwrite'
            {
            match("readwrite"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:57:7: ( 'real' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:57:9: 'real'
            {
            match("real"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:58:7: ( 'refine' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:58:9: 'refine'
            {
            match("refine"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:59:7: ( 'require' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:59:9: 'require'
            {
            match("require"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:60:7: ( 'service' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:60:9: 'service'
            {
            match("service"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:61:7: ( 'set' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:61:9: 'set'
            {
            match("set"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:62:7: ( 'state' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:62:9: 'state'
            {
            match("state"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:63:7: ( 'states' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:63:9: 'states'
            {
            match("states"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:64:7: ( 'string' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:64:9: 'string'
            {
            match("string"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:65:7: ( 'this' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:65:9: 'this'
            {
            match("this"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:66:7: ( 'to' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:66:9: 'to'
            {
            match("to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:67:7: ( 'transition' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:67:9: 'transition'
            {
            match("transition"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:68:7: ( 'transitions' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:68:9: 'transitions'
            {
            match("transitions"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:69:7: ( 'true' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:69:9: 'true'
            {
            match("true"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:70:7: ( 'using' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:70:9: 'using'
            {
            match("using"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:71:7: ( 'void' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:71:9: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:72:7: ( 'when' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:72:9: 'when'
            {
            match("when"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:73:7: ( 'with' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:73:9: 'with'
            {
            match("with"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:74:7: ( 'write' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:74:9: 'write'
            {
            match("write"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:75:7: ( '{' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:75:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:76:7: ( '||' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:76:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:77:7: ( '}' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:77:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:78:7: ( '~' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:78:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "NOTOPERATOR"
    public final void mNOTOPERATOR() throws RecognitionException {
        try {
            int _type = NOTOPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:556:12: ( 'not' | '!' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='n') ) {
                alt1=1;
            }
            else if ( (LA1_0=='!') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:556:14: 'not'
                    {
                    match("not"); 



                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:556:22: '!'
                    {
                    match('!'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOTOPERATOR"

    // $ANTLR start "MULTOP"
    public final void mMULTOP() throws RecognitionException {
        try {
            int _type = MULTOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:8: ( '*' | '/' | 'div' | 'mod' | '%' )
            int alt2=5;
            switch ( input.LA(1) ) {
            case '*':
                {
                alt2=1;
                }
                break;
            case '/':
                {
                alt2=2;
                }
                break;
            case 'd':
                {
                alt2=3;
                }
                break;
            case 'm':
                {
                alt2=4;
                }
                break;
            case '%':
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:10: '*'
                    {
                    match('*'); 

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:16: '/'
                    {
                    match('/'); 

                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:22: 'div'
                    {
                    match("div"); 



                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:30: 'mod'
                    {
                    match("mod"); 



                    }
                    break;
                case 5 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:557:38: '%'
                    {
                    match('%'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULTOP"

    // $ANTLR start "ADDOP"
    public final void mADDOP() throws RecognitionException {
        try {
            int _type = ADDOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:558:7: ( '+' | '-' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ADDOP"

    // $ANTLR start "RELOP"
    public final void mRELOP() throws RecognitionException {
        try {
            int _type = RELOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:7: ( '<=' | '>=' | '>' | '<' | '==' | '!=' | '<>' )
            int alt3=7;
            switch ( input.LA(1) ) {
            case '<':
                {
                switch ( input.LA(2) ) {
                case '=':
                    {
                    alt3=1;
                    }
                    break;
                case '>':
                    {
                    alt3=7;
                    }
                    break;
                default:
                    alt3=4;
                }

                }
                break;
            case '>':
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2=='=') ) {
                    alt3=2;
                }
                else {
                    alt3=3;
                }
                }
                break;
            case '=':
                {
                alt3=5;
                }
                break;
            case '!':
                {
                alt3=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:9: '<='
                    {
                    match("<="); 



                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:16: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:23: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:29: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 5 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:34: '=='
                    {
                    match("=="); 



                    }
                    break;
                case 6 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:41: '!='
                    {
                    match("!="); 



                    }
                    break;
                case 7 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:559:48: '<>'
                    {
                    match("<>"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RELOP"

    // $ANTLR start "QUANTIFIER"
    public final void mQUANTIFIER() throws RecognitionException {
        try {
            int _type = QUANTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:560:11: ( 'forall' | 'FORALL' | 'exists' | 'EXISTS' )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 'f':
                {
                alt4=1;
                }
                break;
            case 'F':
                {
                alt4=2;
                }
                break;
            case 'e':
                {
                alt4=3;
                }
                break;
            case 'E':
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:560:13: 'forall'
                    {
                    match("forall"); 



                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:560:22: 'FORALL'
                    {
                    match("FORALL"); 



                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:560:31: 'exists'
                    {
                    match("exists"); 



                    }
                    break;
                case 4 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:560:40: 'EXISTS'
                    {
                    match("EXISTS"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUANTIFIER"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:562:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:562:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:562:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '0' && LA5_0 <= '9')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||LA5_0=='_'||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:565:5: ( ( '-' )? ( '0' .. '9' )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:565:7: ( '-' )? ( '0' .. '9' )+
            {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:565:7: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:565:8: '-'
                    {
                    match('-'); 

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:565:13: ( '0' .. '9' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:5: ( ( '-' )? ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT ) )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:9: ( '-' )? ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:9: ( '-' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='-') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:15: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt15=3;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:16: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:16: ( '0' .. '9' )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);


                    match('.'); 

                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:32: ( '0' .. '9' )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:44: ( EXPONENT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='E'||LA11_0=='e') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:569:44: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:570:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 

                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:570:13: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


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


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:570:25: ( EXPONENT )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:570:25: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:571:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:571:9: ( '0' .. '9' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


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


                    mEXPONENT(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:575:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='/') ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1=='/') ) {
                    alt19=1;
                }
                else if ( (LA19_1=='*') ) {
                    alt19=2;
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
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:575:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("//"); 



                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:575:14: (~ ( '\\n' | '\\r' ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0 >= '\u0000' && LA16_0 <= '\t')||(LA16_0 >= '\u000B' && LA16_0 <= '\f')||(LA16_0 >= '\u000E' && LA16_0 <= '\uFFFF')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:575:28: ( '\\r' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='\r') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:575:28: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }


                    match('\n'); 

                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:576:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 



                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:576:14: ( options {greedy=false; } : . )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='*') ) {
                            int LA18_1 = input.LA(2);

                            if ( (LA18_1=='/') ) {
                                alt18=2;
                            }
                            else if ( ((LA18_1 >= '\u0000' && LA18_1 <= '.')||(LA18_1 >= '0' && LA18_1 <= '\uFFFF')) ) {
                                alt18=1;
                            }


                        }
                        else if ( ((LA18_0 >= '\u0000' && LA18_0 <= ')')||(LA18_0 >= '+' && LA18_0 <= '\uFFFF')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:576:42: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    match("*/"); 



                    _channel=HIDDEN;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:579:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:579:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:587:5: ( '\"' ( ESC_SEQ |~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:587:8: '\"' ( ESC_SEQ |~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 

            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:587:12: ( ESC_SEQ |~ ( '\\\\' | '\"' ) )*
            loop20:
            do {
                int alt20=3;
                int LA20_0 = input.LA(1);

                if ( (LA20_0=='\\') ) {
                    alt20=1;
                }
                else if ( ((LA20_0 >= '\u0000' && LA20_0 <= '!')||(LA20_0 >= '#' && LA20_0 <= '[')||(LA20_0 >= ']' && LA20_0 <= '\uFFFF')) ) {
                    alt20=2;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:587:14: ESC_SEQ
            	    {
            	    mESC_SEQ(); 


            	    }
            	    break;
            	case 2 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:587:24: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:592:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:592:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:592:22: ( '+' | '-' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='+'||LA21_0=='-') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:592:33: ( '0' .. '9' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0 >= '0' && LA22_0 <= '9')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:595:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:599:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt23=1;
                    }
                    break;
                case 'u':
                    {
                    alt23=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt23=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:599:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:600:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 


                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:601:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:606:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1 >= '0' && LA24_1 <= '3')) ) {
                    int LA24_2 = input.LA(3);

                    if ( ((LA24_2 >= '0' && LA24_2 <= '7')) ) {
                        int LA24_4 = input.LA(4);

                        if ( ((LA24_4 >= '0' && LA24_4 <= '7')) ) {
                            alt24=1;
                        }
                        else {
                            alt24=2;
                        }
                    }
                    else {
                        alt24=3;
                    }
                }
                else if ( ((LA24_1 >= '4' && LA24_1 <= '7')) ) {
                    int LA24_3 = input.LA(3);

                    if ( ((LA24_3 >= '0' && LA24_3 <= '7')) ) {
                        alt24=2;
                    }
                    else {
                        alt24=3;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:606:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:607:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:608:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:613:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:613:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 

            match('u'); 

            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNICODE_ESC"

    public void mTokens() throws RecognitionException {
        // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:8: ( T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | NOTOPERATOR | MULTOP | ADDOP | RELOP | QUANTIFIER | ID | INT | FLOAT | COMMENT | WS | STRING )
        int alt25=78;
        alt25 = dfa25.predict(input);
        switch (alt25) {
            case 1 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:10: T__20
                {
                mT__20(); 


                }
                break;
            case 2 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:16: T__21
                {
                mT__21(); 


                }
                break;
            case 3 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:22: T__22
                {
                mT__22(); 


                }
                break;
            case 4 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:28: T__23
                {
                mT__23(); 


                }
                break;
            case 5 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:34: T__24
                {
                mT__24(); 


                }
                break;
            case 6 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:40: T__25
                {
                mT__25(); 


                }
                break;
            case 7 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:46: T__26
                {
                mT__26(); 


                }
                break;
            case 8 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:52: T__27
                {
                mT__27(); 


                }
                break;
            case 9 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:58: T__28
                {
                mT__28(); 


                }
                break;
            case 10 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:64: T__29
                {
                mT__29(); 


                }
                break;
            case 11 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:70: T__30
                {
                mT__30(); 


                }
                break;
            case 12 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:76: T__31
                {
                mT__31(); 


                }
                break;
            case 13 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:82: T__32
                {
                mT__32(); 


                }
                break;
            case 14 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:88: T__33
                {
                mT__33(); 


                }
                break;
            case 15 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:94: T__34
                {
                mT__34(); 


                }
                break;
            case 16 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:100: T__35
                {
                mT__35(); 


                }
                break;
            case 17 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:106: T__36
                {
                mT__36(); 


                }
                break;
            case 18 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:112: T__37
                {
                mT__37(); 


                }
                break;
            case 19 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:118: T__38
                {
                mT__38(); 


                }
                break;
            case 20 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:124: T__39
                {
                mT__39(); 


                }
                break;
            case 21 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:130: T__40
                {
                mT__40(); 


                }
                break;
            case 22 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:136: T__41
                {
                mT__41(); 


                }
                break;
            case 23 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:142: T__42
                {
                mT__42(); 


                }
                break;
            case 24 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:148: T__43
                {
                mT__43(); 


                }
                break;
            case 25 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:154: T__44
                {
                mT__44(); 


                }
                break;
            case 26 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:160: T__45
                {
                mT__45(); 


                }
                break;
            case 27 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:166: T__46
                {
                mT__46(); 


                }
                break;
            case 28 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:172: T__47
                {
                mT__47(); 


                }
                break;
            case 29 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:178: T__48
                {
                mT__48(); 


                }
                break;
            case 30 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:184: T__49
                {
                mT__49(); 


                }
                break;
            case 31 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:190: T__50
                {
                mT__50(); 


                }
                break;
            case 32 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:196: T__51
                {
                mT__51(); 


                }
                break;
            case 33 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:202: T__52
                {
                mT__52(); 


                }
                break;
            case 34 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:208: T__53
                {
                mT__53(); 


                }
                break;
            case 35 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:214: T__54
                {
                mT__54(); 


                }
                break;
            case 36 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:220: T__55
                {
                mT__55(); 


                }
                break;
            case 37 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:226: T__56
                {
                mT__56(); 


                }
                break;
            case 38 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:232: T__57
                {
                mT__57(); 


                }
                break;
            case 39 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:238: T__58
                {
                mT__58(); 


                }
                break;
            case 40 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:244: T__59
                {
                mT__59(); 


                }
                break;
            case 41 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:250: T__60
                {
                mT__60(); 


                }
                break;
            case 42 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:256: T__61
                {
                mT__61(); 


                }
                break;
            case 43 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:262: T__62
                {
                mT__62(); 


                }
                break;
            case 44 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:268: T__63
                {
                mT__63(); 


                }
                break;
            case 45 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:274: T__64
                {
                mT__64(); 


                }
                break;
            case 46 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:280: T__65
                {
                mT__65(); 


                }
                break;
            case 47 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:286: T__66
                {
                mT__66(); 


                }
                break;
            case 48 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:292: T__67
                {
                mT__67(); 


                }
                break;
            case 49 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:298: T__68
                {
                mT__68(); 


                }
                break;
            case 50 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:304: T__69
                {
                mT__69(); 


                }
                break;
            case 51 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:310: T__70
                {
                mT__70(); 


                }
                break;
            case 52 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:316: T__71
                {
                mT__71(); 


                }
                break;
            case 53 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:322: T__72
                {
                mT__72(); 


                }
                break;
            case 54 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:328: T__73
                {
                mT__73(); 


                }
                break;
            case 55 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:334: T__74
                {
                mT__74(); 


                }
                break;
            case 56 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:340: T__75
                {
                mT__75(); 


                }
                break;
            case 57 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:346: T__76
                {
                mT__76(); 


                }
                break;
            case 58 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:352: T__77
                {
                mT__77(); 


                }
                break;
            case 59 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:358: T__78
                {
                mT__78(); 


                }
                break;
            case 60 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:364: T__79
                {
                mT__79(); 


                }
                break;
            case 61 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:370: T__80
                {
                mT__80(); 


                }
                break;
            case 62 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:376: T__81
                {
                mT__81(); 


                }
                break;
            case 63 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:382: T__82
                {
                mT__82(); 


                }
                break;
            case 64 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:388: T__83
                {
                mT__83(); 


                }
                break;
            case 65 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:394: T__84
                {
                mT__84(); 


                }
                break;
            case 66 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:400: T__85
                {
                mT__85(); 


                }
                break;
            case 67 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:406: T__86
                {
                mT__86(); 


                }
                break;
            case 68 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:412: NOTOPERATOR
                {
                mNOTOPERATOR(); 


                }
                break;
            case 69 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:424: MULTOP
                {
                mMULTOP(); 


                }
                break;
            case 70 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:431: ADDOP
                {
                mADDOP(); 


                }
                break;
            case 71 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:437: RELOP
                {
                mRELOP(); 


                }
                break;
            case 72 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:443: QUANTIFIER
                {
                mQUANTIFIER(); 


                }
                break;
            case 73 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:454: ID
                {
                mID(); 


                }
                break;
            case 74 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:457: INT
                {
                mINT(); 


                }
                break;
            case 75 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:461: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 76 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:467: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 77 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:475: WS
                {
                mWS(); 


                }
                break;
            case 78 :
                // /home/hbelhaou/recherche/Tamago/TamagoCC/source/tamagocc/compiler/TamagoCDL.g:1:478: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA25 dfa25 = new DFA25(this);
    static final String DFA15_eotS =
        "\5\uffff";
    static final String DFA15_eofS =
        "\5\uffff";
    static final String DFA15_minS =
        "\2\56\3\uffff";
    static final String DFA15_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA15_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA15_specialS =
        "\5\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "569:15: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )";
        }
    }
    static final String DFA25_eotS =
        "\7\uffff\1\60\3\uffff\1\51\1\66\1\uffff\17\51\4\uffff\1\130\1\43"+
        "\1\uffff\1\52\1\uffff\3\51\2\uffff\1\135\10\uffff\1\51\2\uffff\1"+
        "\51\1\140\11\51\1\154\1\51\1\161\14\51\1\u0083\6\51\2\uffff\3\51"+
        "\1\uffff\2\51\1\uffff\6\51\1\43\4\51\1\uffff\2\51\1\u009c\1\51\1"+
        "\uffff\1\51\1\43\1\u00a0\1\51\1\130\2\51\1\u00a4\5\51\1\u00ac\3"+
        "\51\1\uffff\15\51\1\u00be\1\u00bf\3\51\1\u00c3\1\51\1\u00c5\3\51"+
        "\1\uffff\3\51\1\uffff\1\u00cc\1\51\1\u00ce\1\uffff\2\51\1\u00d2"+
        "\1\u00d3\3\51\1\uffff\2\51\1\u00d9\1\51\1\u00db\1\51\1\u00dd\1\u00de"+
        "\1\u00df\5\51\1\u00e5\2\51\2\uffff\3\51\1\uffff\1\u00eb\1\uffff"+
        "\6\51\1\uffff\1\51\1\uffff\3\51\2\uffff\3\51\1\u00fa\1\51\1\uffff"+
        "\1\51\1\uffff\1\u00fd\3\uffff\1\u00fe\3\51\1\u0102\1\uffff\4\51"+
        "\1\u0107\1\uffff\1\u0108\3\51\1\u010c\1\u010d\4\51\1\u0112\2\51"+
        "\1\u0115\1\uffff\1\u0116\1\51\2\uffff\3\u0108\1\uffff\1\51\1\u0119"+
        "\1\51\1\u011b\2\uffff\1\51\1\u011d\1\51\2\uffff\2\51\1\u0121\1\51"+
        "\1\uffff\1\u0123\1\u0124\2\uffff\1\51\1\u0126\1\uffff\1\51\1\uffff"+
        "\1\51\1\uffff\2\51\1\u012b\1\uffff\1\51\2\uffff\1\51\1\uffff\1\u012e"+
        "\1\51\1\u0130\1\51\1\uffff\1\u0132\1\51\1\uffff\1\u0134\1\uffff"+
        "\1\u0135\1\uffff\1\u0137\2\uffff\1\u0138\2\uffff";
    static final String DFA25_eofS =
        "\u0139\uffff";
    static final String DFA25_minS =
        "\1\11\6\uffff\1\52\2\uffff\1\160\1\164\1\135\1\uffff\1\154\1\145"+
        "\1\157\1\145\1\141\1\144\1\145\1\151\3\145\1\150\1\163\1\157\1\150"+
        "\4\uffff\1\75\1\52\1\uffff\1\56\1\uffff\1\117\1\170\1\130\2\uffff"+
        "\1\56\10\uffff\1\162\2\uffff\1\154\1\60\1\150\1\157\1\154\1\146"+
        "\1\165\1\166\1\151\1\157\1\162\1\60\1\160\1\60\1\164\1\144\2\154"+
        "\1\164\1\162\1\163\1\145\1\141\1\162\1\141\1\151\1\60\1\141\2\151"+
        "\1\145\1\164\1\151\2\uffff\1\122\1\151\1\111\1\uffff\1\151\1\157"+
        "\1\uffff\1\141\2\154\1\160\1\141\1\142\1\60\1\154\1\163\1\155\1"+
        "\141\1\uffff\2\154\1\60\1\141\1\uffff\1\150\2\60\1\154\1\60\1\143"+
        "\1\164\1\60\1\160\1\144\1\151\1\165\1\166\1\60\1\164\1\151\1\163"+
        "\1\uffff\1\156\1\145\1\156\1\144\1\156\1\150\1\164\1\101\1\163\1"+
        "\123\1\156\1\167\1\166\2\60\1\157\1\165\1\154\1\60\1\145\1\60\1"+
        "\154\1\145\1\165\1\uffff\1\162\1\157\1\154\1\uffff\1\60\1\157\1"+
        "\60\1\uffff\1\145\1\151\2\60\1\156\2\151\1\uffff\1\145\1\156\1\60"+
        "\1\163\1\60\1\147\3\60\1\145\1\114\1\164\1\124\1\147\1\60\1\151"+
        "\1\141\2\uffff\1\156\1\154\1\145\1\uffff\1\60\1\uffff\1\154\1\155"+
        "\1\144\1\151\1\144\1\145\1\uffff\1\154\1\uffff\1\162\1\144\1\162"+
        "\2\uffff\1\145\1\162\1\143\1\60\1\147\1\uffff\1\151\1\uffff\1\60"+
        "\3\uffff\1\60\1\114\1\163\1\123\1\60\1\uffff\1\157\1\156\1\145\1"+
        "\164\1\60\1\uffff\1\60\2\145\1\141\2\60\1\141\1\164\1\145\1\151"+
        "\1\60\2\145\1\60\1\uffff\1\60\1\164\2\uffff\3\60\1\uffff\1\162\1"+
        "\60\1\156\1\60\2\uffff\1\156\1\60\1\156\2\uffff\1\164\1\171\1\60"+
        "\1\164\1\uffff\2\60\2\uffff\1\151\1\60\1\uffff\1\164\1\uffff\1\164"+
        "\1\uffff\1\164\1\157\1\60\1\uffff\1\145\2\uffff\1\157\1\uffff\1"+
        "\60\1\163\1\60\1\162\1\uffff\1\60\1\156\1\uffff\1\60\1\uffff\1\60"+
        "\1\uffff\1\60\2\uffff\1\60\2\uffff";
    static final String DFA25_maxS =
        "\1\176\6\uffff\1\71\2\uffff\1\162\1\164\1\135\1\uffff\1\163\3\157"+
        "\1\162\1\156\1\157\1\165\1\162\1\145\1\164\1\162\1\163\1\157\1\162"+
        "\4\uffff\1\75\1\57\1\uffff\1\71\1\uffff\1\117\1\170\1\130\2\uffff"+
        "\1\145\10\uffff\1\162\2\uffff\1\154\1\172\1\150\1\157\1\155\1\146"+
        "\1\165\1\166\1\154\1\157\1\162\1\172\1\160\1\172\1\164\1\144\2\154"+
        "\1\164\1\162\1\163\1\157\1\161\1\164\1\162\1\151\1\172\1\165\2\151"+
        "\1\145\1\164\1\151\2\uffff\1\122\1\151\1\111\1\uffff\1\151\1\157"+
        "\1\uffff\1\141\2\154\1\160\1\141\1\142\1\172\1\154\1\163\1\155\1"+
        "\141\1\uffff\2\154\1\172\1\141\1\uffff\1\150\2\172\1\154\1\172\1"+
        "\143\1\164\1\172\1\166\1\154\1\151\1\165\1\166\1\172\1\164\1\151"+
        "\1\163\1\uffff\1\156\1\145\1\156\1\144\1\156\1\150\1\164\1\101\1"+
        "\163\1\123\1\156\1\167\1\166\2\172\1\157\1\165\1\154\1\172\1\145"+
        "\1\172\1\154\1\145\1\165\1\uffff\1\162\1\157\1\154\1\uffff\1\172"+
        "\1\157\1\172\1\uffff\1\145\1\151\2\172\1\156\2\151\1\uffff\1\145"+
        "\1\156\1\172\1\163\1\172\1\147\3\172\1\145\1\114\1\164\1\124\1\147"+
        "\1\172\1\151\1\141\2\uffff\1\156\1\154\1\145\1\uffff\1\172\1\uffff"+
        "\1\154\1\155\1\144\1\151\1\144\1\145\1\uffff\1\154\1\uffff\1\162"+
        "\1\144\1\162\2\uffff\1\145\1\162\1\143\1\172\1\147\1\uffff\1\151"+
        "\1\uffff\1\172\3\uffff\1\172\1\114\1\163\1\123\1\172\1\uffff\1\157"+
        "\1\156\1\145\1\164\1\172\1\uffff\1\172\2\145\1\141\2\172\1\141\1"+
        "\164\1\145\1\151\1\172\2\145\1\172\1\uffff\1\172\1\164\2\uffff\3"+
        "\172\1\uffff\1\162\1\172\1\156\1\172\2\uffff\1\156\1\172\1\156\2"+
        "\uffff\1\164\1\171\1\172\1\164\1\uffff\2\172\2\uffff\1\151\1\172"+
        "\1\uffff\1\164\1\uffff\1\164\1\uffff\1\164\1\157\1\172\1\uffff\1"+
        "\145\2\uffff\1\157\1\uffff\1\172\1\163\1\172\1\162\1\uffff\1\172"+
        "\1\156\1\uffff\1\172\1\uffff\1\172\1\uffff\1\172\2\uffff\1\172\2"+
        "\uffff";
    static final String DFA25_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\1\12\3\uffff\1\20"+
        "\17\uffff\1\100\1\101\1\102\1\103\2\uffff\1\105\1\uffff\1\107\3"+
        "\uffff\1\111\1\106\1\uffff\1\115\1\116\1\5\1\10\1\7\1\113\1\13\1"+
        "\14\1\uffff\1\17\1\16\41\uffff\1\104\1\114\3\uffff\1\112\2\uffff"+
        "\1\22\13\uffff\1\35\4\uffff\1\37\21\uffff\1\67\30\uffff\1\41\3\uffff"+
        "\1\45\3\uffff\1\51\7\uffff\1\62\21\uffff\1\24\1\26\3\uffff\1\32"+
        "\1\uffff\1\34\6\uffff\1\46\1\uffff\1\50\3\uffff\1\54\1\56\5\uffff"+
        "\1\66\1\uffff\1\72\1\uffff\1\74\1\75\1\76\5\uffff\1\21\5\uffff\1"+
        "\33\16\uffff\1\63\2\uffff\1\73\1\77\3\uffff\1\15\4\uffff\1\31\1"+
        "\110\3\uffff\1\43\1\44\4\uffff\1\57\2\uffff\1\64\1\65\2\uffff\1"+
        "\25\1\uffff\1\30\1\uffff\1\40\3\uffff\1\53\1\uffff\1\60\1\61\1\uffff"+
        "\1\23\4\uffff\1\52\2\uffff\1\27\1\uffff\1\42\1\uffff\1\55\1\uffff"+
        "\1\36\1\47\1\uffff\1\70\1\71";
    static final String DFA25_specialS =
        "\u0139\uffff}>";
    static final String[] DFA25_transitionS = {
            "\2\54\2\uffff\1\54\22\uffff\1\54\1\41\1\55\1\1\1\uffff\1\43"+
            "\1\2\1\uffff\1\3\1\4\1\5\1\52\1\6\1\44\1\7\1\42\12\53\1\10\1"+
            "\11\3\45\1\uffff\1\12\4\51\1\50\1\46\14\51\1\13\7\51\1\14\1"+
            "\uffff\1\15\1\uffff\1\51\1\uffff\1\16\1\17\1\20\1\21\1\47\1"+
            "\22\2\51\1\23\3\51\1\24\1\25\1\51\1\26\1\51\1\27\1\30\1\31\1"+
            "\32\1\33\1\34\3\51\1\35\1\36\1\37\1\40",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\57\5\uffff\12\61",
            "",
            "",
            "\1\62\1\uffff\1\63",
            "\1\64",
            "\1\65",
            "",
            "\1\67\6\uffff\1\70",
            "\1\71\11\uffff\1\72",
            "\1\73",
            "\1\74\3\uffff\1\76\5\uffff\1\75",
            "\1\77\15\uffff\1\101\2\uffff\1\100",
            "\1\102\10\uffff\1\103\1\104",
            "\1\105\11\uffff\1\106",
            "\1\107\5\uffff\1\111\5\uffff\1\110",
            "\1\112\11\uffff\1\113\2\uffff\1\114",
            "\1\115",
            "\1\116\16\uffff\1\117",
            "\1\120\6\uffff\1\121\2\uffff\1\122",
            "\1\123",
            "\1\124",
            "\1\125\1\126\10\uffff\1\127",
            "",
            "",
            "",
            "",
            "\1\45",
            "\1\131\4\uffff\1\131",
            "",
            "\1\61\1\uffff\12\53",
            "",
            "\1\132",
            "\1\133",
            "\1\134",
            "",
            "",
            "\1\61\1\uffff\12\53\13\uffff\1\61\37\uffff\1\61",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\136",
            "",
            "",
            "\1\137",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\141",
            "\1\142",
            "\1\143\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150\2\uffff\1\151",
            "\1\152",
            "\1\153",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\155",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\2\51\1\156\20\51"+
            "\1\157\1\51\1\160\4\51",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171\11\uffff\1\172",
            "\1\173\4\uffff\1\174\12\uffff\1\175",
            "\1\176\1\uffff\1\177",
            "\1\u0080\20\uffff\1\u0081",
            "\1\u0082",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0084\23\uffff\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "",
            "",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "",
            "\1\u008e",
            "\1\u008f",
            "",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "",
            "\1\u009a",
            "\1\u009b",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u009d",
            "",
            "\1\u009e",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\24\51\1\u009f\5\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00a1",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00a2",
            "\1\u00a3",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00a5\5\uffff\1\u00a6",
            "\1\u00a7\7\uffff\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\4\51\1\u00bd\25\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00c4",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00cd",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u00cf",
            "\1\u00d0",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\26\51\1\u00d1\3\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "\1\u00d7",
            "\1\u00d8",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00da",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00dc",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00e6",
            "\1\u00e7",
            "",
            "",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "",
            "\1\u00f2",
            "",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "",
            "",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\22\51\1\u00f9\7\51",
            "\1\u00fb",
            "",
            "\1\u00fc",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0113",
            "\1\u0114",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0117",
            "",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u0118",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u011a",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\1\u011c",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u011e",
            "",
            "",
            "\1\u011f",
            "\1\u0120",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0122",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\1\u0125",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u0127",
            "",
            "\1\u0128",
            "",
            "\1\u0129",
            "\1\u012a",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\1\u012c",
            "",
            "",
            "\1\u012d",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u012f",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0131",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0133",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\22\51\1\u0136\7\51",
            "",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | NOTOPERATOR | MULTOP | ADDOP | RELOP | QUANTIFIER | ID | INT | FLOAT | COMMENT | WS | STRING );";
        }
    }
 

}