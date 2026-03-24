package jplus.parser;

import jplus.base.JADEx25Lexer;
import jplus.base.JADEx25Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

public class JADExParserFactory {

    private static final PredictionContextCache SHARED_CONTEXT_CACHE = new PredictionContextCache();
    private static final DFA[] LEXER_DFA;
    private static final DFA[] PARSER_DFA;

    static {
        int lexerDecisions = JADEx25Lexer._ATN.getNumberOfDecisions();
        LEXER_DFA = new DFA[lexerDecisions];
        for (int i = 0; i < lexerDecisions; i++) {
            LEXER_DFA[i] = new DFA(JADEx25Lexer._ATN.getDecisionState(i), i);
        }

        int parserDecisions = JADEx25Parser._ATN.getNumberOfDecisions();
        PARSER_DFA = new DFA[parserDecisions];
        for (int i = 0; i < parserDecisions; i++) {
            PARSER_DFA[i] = new DFA(JADEx25Parser._ATN.getDecisionState(i), i);
        }
    }

    public static JADEx25Lexer createLexer(CharStream input) {
        JADEx25Lexer lexer = new JADEx25Lexer(input);

        lexer.setInterpreter(new LexerATNSimulator(
                lexer,
                JADEx25Lexer._ATN,
                LEXER_DFA,
                SHARED_CONTEXT_CACHE
        ));

        return lexer;
    }

    public static JADEx25Parser createParser(TokenStream tokens) {
        JADEx25Parser parser = new JADEx25Parser(tokens);

        parser.setInterpreter(new ParserATNSimulator(
                parser,
                JADEx25Parser._ATN,
                PARSER_DFA,
                SHARED_CONTEXT_CACHE
        ));

        return parser;
    }
}