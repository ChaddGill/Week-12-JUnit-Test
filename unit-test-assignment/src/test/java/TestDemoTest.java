import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static  org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class TestDemoTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {

		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly
		(int a, int b, int expected, Boolean expectException) {

		if (!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {

			assertThatThrownBy(
					() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);

		}
	}

	static Stream<Arguments> argumentsForAddPositive() {

		// @formatter:off
		return Stream.of(
			arguments(1,2,3,false),
			arguments(-2,3,1,true),
			arguments(5,-3,2,true),
			arguments(0,4,4,true),
			arguments(4,16,20,false),
			arguments(4,20,24,false),
			arguments(2,0,2,true),
			arguments(0,0,0,true),
			arguments(-2,-4,-6,true),
			arguments(-2,4,2,true),
			arguments(2,-4,-2,true),
			arguments(2,4,6,false)
			);
		// @formatter:on

	}

	@Test
	void assertThatNumberSquaredIsCorrect() {

		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);

	}
}
