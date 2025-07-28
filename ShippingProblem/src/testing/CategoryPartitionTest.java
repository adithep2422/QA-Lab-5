package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import sqa.main.ShippingVehicle;

import java.util.List;
import java.util.stream.Stream;

class CategoryPartitionTest {
	
	ShippingVehicle test;

	@BeforeEach
	void setup() {
		test = new ShippingVehicle();
	}

	@ParameterizedTest
	@MethodSource("shippingVehicle")
	void test_category(int small, int medium, int large, List<Integer> expected) {
		assertEquals(expected, test.calculate(small, medium, large));
	}

	static Stream<Arguments> shippingVehicle() {
		return Stream.of(
			// valid input
			Arguments.of(0, 0, 0, List.of(0, 0, 0)),

			Arguments.of(0, 0, 65, List.of(65, 0, 0)), 
			Arguments.of(0, 133, 0, List.of(0, 133, 0)),
			Arguments.of(455, 0, 0, List.of(0, 0, 455)),

			Arguments.of(0, 87, 47, List.of(47, 87, 0)), 
			Arguments.of(237, 81, 0, List.of(0, 81, 237)),

			Arguments.of(123, 63, 30, List.of(30, 63, 123)),

			// invalid input
			Arguments.of(-2, 0, 0, List.of(-1)), 
			Arguments.of(0, -3, 0, List.of(-1)),
			Arguments.of(0, 0, -4, List.of(-1)),

			Arguments.of(-1, -5, 0, List.of(-1)),
			Arguments.of(0, -7, -2, List.of(-1)), 
			Arguments.of(-8, -6, -9, List.of(-1)),

			// over weight
			Arguments.of(0, 0, 102, List.of(-1)), 
			Arguments.of(0, 211, 0, List.of(-1)),
			Arguments.of(521, 0, 0, List.of(-1)),

			Arguments.of(0, 103, 52, List.of(-1)), 
			Arguments.of(261, 102, 0, List.of(-1)),

			Arguments.of(171, 65, 58, List.of(-1))
		);

	}
}