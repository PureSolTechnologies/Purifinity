/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.purifinity.utils.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Random;

/**
 * This class is used to generate random numbers with different conditions.
 * java.utils.Random is used to create base random numbers. A lot of
 * different methods are provided to create random numbers with mavericks
 * (outlayers), trends and steps for testing statistical calculations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RandomNumbers {

    /**
     * This is the internal generator for creating random numbers. The
     * java.util.Random generator is used to create base random numbers
     * with normal and constant distribution for interager and double
     * variables.
     */
    private Random generator;

    /**
     * This is the standard constructor for creating a new random number
     * generator without setting a seed. The seed is generated regarding to
     * the current system time.
     */
    public RandomNumbers() {
	generator = new Random();
    }

    /**
     * This is the standard constructor for creating a new random number
     * generator without setting a seed. The seed is generated regarding to
     * the current system time.
     * 
     * @param seed
     *            is the random see to be used for reproducible random
     *            number for test cases.
     */
    public RandomNumbers(int seed) {
	generator = new Random(seed);
    }

    /**
     * This method converts a value into an outlayer. The max size of the
     * spike is specified.
     * 
     * @param d
     *            is the value to be converted into an outlayer.
     * @param outlayerSize
     *            is the maximum size of the spike.
     * @return An outlayer is returned.
     */
    public double generateOutlayer(double d, double outlayerSize) {
	d += getGaussian(0.0, outlayerSize);
	return d;
    }

    /**
     * This method returns a double random number in interval of [0.0,
     * 1.0).
     * 
     * @see java.util.Random#nextDouble()
     * 
     * @return A double random number is returned in [0.0, 1.0).
     */
    public double getDouble() {
	return generator.nextDouble();
    }

    public double getDouble(double min, double max) {
	return generator.nextDouble() * (max - min) + min;
    }

    public float getFloat() {
	return generator.nextFloat();
    }

    public double getFloat(double min, double max) {
	return generator.nextFloat() * (max - min) + min;
    }

    /**
     * This method returns a int random number.
     * 
     * @see java.util.Random#nextInt()
     * 
     * @return A int random number is returned.
     */
    public int getInt() {
	return generator.nextInt();
    }

    public int getInt(int min, int max) {
	return generator.nextInt(max - min) + min;
    }

    /**
     * This method returns a int random number in interval [0, max).
     * 
     * @see java.util.Random#nextInt(int)
     * 
     * @param max
     *            is the maximum int number for interval. Attention(!):
     *            This number is the upper part of a semi open interval and
     *            therefore never returned!
     * 
     * @return A int random number is returned.
     * 
     * @throws IllegalArgumentException
     *             is thrown in case of bad defined arguments.
     */
    public int getInt(int max) throws IllegalArgumentException {
	if (max < 1) {
	    throw new IllegalArgumentException("max is <1");
	}
	return generator.nextInt(max);
    }

    public long getLong() {
	return generator.nextLong();
    }

    /**
     * This method generates random number in gaussian distribution. The
     * average for these random numbers is zero and the standard deviation
     * is 1.
     * 
     * @see java.util.Random
     * 
     * @return A double variable with the random number is returned.
     */
    public double getGaussian() {
	return generator.nextGaussian();
    }

    /**
     * This method generates random number in gaussian distribution. The
     * average for these random numbers is set to avg and the standard
     * deviation is stdDev.
     * 
     * @see java.util.Random
     * 
     * @param avg
     *            is the average of the random numbers to get with a large
     *            number of random numbers.
     * 
     * @param stdDev
     *            is the standard deviation of the random numbers to get
     *            with a large number of random numbers. This value must be
     *            greater than 0.
     * 
     * @return A double variable with the random number is returned.
     * 
     * @throws IllegalArgumentException
     *             is thrown in case of bad defined arguments.
     */
    public double getGaussian(double avg, double stdDev)
	    throws IllegalArgumentException {
	if (stdDev <= 0.0) {
	    throw new IllegalArgumentException("stdDev is <=0.0");
	}
	return (getGaussian() * stdDev + avg);
    }

    /**
     * This method generates random number in gaussian distribution. The
     * average for these random numbers is set to avg and the standard
     * deviation is stdDev.
     * 
     * @see java.util.Random
     * 
     * @param avg
     *            is the average of the random numbers to get with a large
     *            number of random numbers.
     * 
     * @param stdDev
     *            is the standard deviation of the random numbers to get
     *            with a large number of random numbers. This value must be
     *            greater than 0.
     * 
     * @param outlayerPropability
     *            is the propability a outlayer (maverick) will be
     *            generated. Values are exceptable from 0.0 to 1.0.
     * 
     * @param outlayerSize
     *            is a factor to specified the difference to the average.
     *            Beware: The outlayers are random numbers, too.
     * 
     * @return A double variable with the random number is returned.
     * 
     * @throws IllegalArgumentException
     *             is thrown in case of bad defined arguments.
     */
    public double getGaussian(double avg, double stdDev,
	    double outlayerPropability, double outlayerSize)
	    throws IllegalArgumentException {
	if (outlayerPropability < 0.0) {
	    throw new IllegalArgumentException("probability is <0.0");
	}
	if (outlayerPropability > 1.0) {
	    throw new IllegalArgumentException("probability is >1.0");
	}
	double random = getGaussian(avg, stdDev);
	if (getDouble() < outlayerPropability) {
	    random = generateOutlayer(random, outlayerSize);
	}
	return random;
    }

    /**
     * This method generates a trend of random numbers. The number of data
     * points are specified and also the start and end average and standard
     * deviation.
     * 
     * @param number
     *            this is the number of datapoints to be generated. The
     *            number has to be 2 or greater.
     * 
     * @param avgStart
     *            is the average of the values at the begin of the trend
     *            series.
     * 
     * @param avgEnd
     *            is the average of the values at the end of the trend
     *            series.
     * 
     * @param stdDevStart
     *            is the standard deviation at the begin of the trend
     *            series. This value must be greater than 0.
     * 
     * @param stdDevEnd
     *            is the standard deviation at the end of the trend series.
     *            This value must be greater than 0.
     * 
     * @param outlayerPropability
     *            is the propability a outlayer (maverick) will be
     *            generated. Values are exceptable from 0.0 to 1.0.
     * 
     * @param outlayerSize
     *            is a factor to specified the difference to the average.
     *            Beware: The outlayers are random numbers, too.
     * 
     * @return A double array is returned containing all generated values.
     * 
     * @throws IllegalArgumentException
     *             is thrown in case of bad defined arguments.
     */
    public double[] getGaussianTrend(int number, double avgStart,
	    double avgEnd, double stdDevStart, double stdDevEnd,
	    double outlayerPropability, double outlayerSize)
	    throws IllegalArgumentException {
	if (number < 2) {
	    throw new IllegalArgumentException("number is <2");
	}
	if (stdDevStart <= 0.0) {
	    throw new IllegalArgumentException("stdDevStart is <=0.0");
	}
	if (stdDevEnd <= 0.0) {
	    throw new IllegalArgumentException("stdDevEnd is <=0.0");
	}

	/*
	 * calculate the trend parameters like: y = m x + n
	 */
	double mAvg = (avgEnd - avgStart) / (number - 1);
	double nAvg = avgStart;
	double mStdDev = (stdDevEnd - stdDevStart) / (number - 1);
	double nStdDev = stdDevStart;

	double[] values = new double[number];

	for (int index = 0; index < number; index++) {
	    values[index] =
		    getGaussian(index * mAvg + nAvg, index * mStdDev
			    + nStdDev);
	    if (getDouble() < outlayerPropability) {
		values[index] =
			generateOutlayer(values[index], outlayerSize);

	    }
	}
	return values;
    }

    public Object getRandomValue(Class<?> clazz) {
	if (clazz.equals(Byte.class)) {
	    return new Byte((byte) getInt(-127, 128));
	} else if (clazz.equals(Short.class)) {
	    return new Short((short) getInt(-32768, 32767));
	} else if (clazz.equals(Integer.class)) {
	    return new Integer(generator.nextInt());
	} else if (clazz.equals(Long.class)) {
	    return new Long(generator.nextLong());
	} else if (clazz.equals(Float.class)) {
	    return new Float(generator.nextFloat());
	} else if (clazz.equals(Double.class)) {
	    return new Double(generator.nextDouble());
	} else if (clazz.equals(Character.class)) {
	    return new Character((char) getInt(0, 255));
	} else if (clazz.equals(Boolean.class)) {
	    return new Boolean(generator.nextBoolean());
	} else if (clazz.equals(byte.class)) {
	    return (byte) getInt(-128, 127);
	} else if (clazz.equals(short.class)) {
	    return (short) getInt(-32768, 32767);
	} else if (clazz.equals(int.class)) {
	    return generator.nextInt();
	} else if (clazz.equals(long.class)) {
	    return generator.nextLong();
	} else if (clazz.equals(float.class)) {
	    return generator.nextFloat();
	} else if (clazz.equals(double.class)) {
	    return generator.nextDouble();
	} else if (clazz.equals(char.class)) {
	    return (char) getInt(0, 255);
	} else if (clazz.equals(boolean.class)) {
	    return generator.nextBoolean();
	}

	if (clazz.equals(String.class)) {
	    byte[] bytes = new byte[32];
	    for (int index = 0; index < 32; index++) {
		bytes[index] = (byte) (65 + generator.nextInt(26));
	    }
	    return new String(bytes);
	}

	if (clazz.equals(Date.class)) {
	    return new Date(generator.nextLong());
	}

	try {
	    Constructor<?> constructor = clazz.getConstructor();
	    return constructor.newInstance();
	} catch (SecurityException e) {
	} catch (NoSuchMethodException e) {
	} catch (IllegalArgumentException e) {
	} catch (InstantiationException e) {
	} catch (IllegalAccessException e) {
	} catch (InvocationTargetException e) {
	}
	return null;
    }

    public Object getRandomValue(Class<?> clazz, double min, double max) {
	if (clazz.equals(Byte.class)) {
	    return new Byte((byte) getInt((int) min, (int) max));
	} else if (clazz.equals(Short.class)) {
	    return new Short((short) getInt((int) min, (int) max));
	} else if (clazz.equals(Integer.class)) {
	    return new Integer(getInt((int) min, (int) max));
	} else if (clazz.equals(Long.class)) {
	    return new Long(getInt((int) min, (int) max));
	} else if (clazz.equals(Float.class)) {
	    return new Float(getFloat(min, max));
	} else if (clazz.equals(Double.class)) {
	    return new Double(getDouble(min, max));
	} else if (clazz.equals(Character.class)) {
	    return new Character((char) getInt((int) min, (int) max));
	} else if (clazz.equals(Boolean.class)) {
	    return new Boolean(generator.nextBoolean());
	} else if (clazz.equals(byte.class)) {
	    return (byte) getInt((int) min, (int) max);
	} else if (clazz.equals(short.class)) {
	    return (short) getInt((int) min, (int) max);
	} else if (clazz.equals(int.class)) {
	    return getInt((int) min, (int) max);
	} else if (clazz.equals(long.class)) {
	    return getInt((int) min, (int) max);
	} else if (clazz.equals(float.class)) {
	    return getFloat(min, max);
	} else if (clazz.equals(double.class)) {
	    return getDouble(min, max);
	} else if (clazz.equals(char.class)) {
	    return (char) getInt((int) min, (int) max);
	} else if (clazz.equals(boolean.class)) {
	    return generator.nextBoolean();
	}

	if (clazz.equals(String.class)) {
	    byte[] bytes = new byte[32];
	    for (int index = 0; index < 32; index++) {
		bytes[index] = (byte) (65 + generator.nextInt(26));
	    }
	    return new String(bytes);
	}

	if (clazz.equals(Date.class)) {
	    return new Date(generator.nextLong());
	}

	try {
	    Constructor<?> constructor = clazz.getConstructor();
	    return constructor.newInstance();
	} catch (SecurityException e) {
	} catch (NoSuchMethodException e) {
	} catch (IllegalArgumentException e) {
	} catch (InstantiationException e) {
	} catch (IllegalAccessException e) {
	} catch (InvocationTargetException e) {
	}
	return null;
    }
}
