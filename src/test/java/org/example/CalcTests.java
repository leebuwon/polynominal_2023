package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CalcTests {

    @Test
    @DisplayName("1 + 1 = 2")
    void t1(){
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 = 3")
    void t2(){
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 = 4")
    void t3(){
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("100 + 200 = 300")
    void t4(){
        assertThat(Calc.run("100 + 200")).isEqualTo(300);
    }

    @Test
    @DisplayName("50 - 30 = 20")
    void t5(){
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("3 - 2 = 1")
    void t6(){
        assertThat(Calc.run("3 - 2")).isEqualTo(1);
    }
}
