package CoreMath;

import java.util.Date;
import java.util.StringTokenizer;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:08:28
 * To change this template use File | Settings | File Templates.
 */
public class PolyEval {

public static final int MAXDEG = 100;
	public static final int MAXVALS = 50;


	public static void main( String args[] ) throws IOException
	{
		long before1 = getTime();
		evalPolyTest1( "input.txt", "output1.txt" );
		long after1 = getTime();
		long diff1 = after1-before1;

		// running my algorithm twice just to make sure there are no disadvantages with program startup and initial file read
		before1 = getTime();
		evalPolyTest1( "input.txt", "output1.txt" );
		after1 = getTime();
		diff1 = after1-before1;

		long before2 = getTime();
		evalPolyTest2( "input.txt", "output2.txt" );
		long after2 = getTime();
		long diff2 = after2-before2;

        // getting a baseline time:  just file operations, no polynomial evaluations
		long beforeBase = getTime();
		baseLine( "input.txt", "output3.txt" );
		long afterBase = getTime();
		long diffBase = afterBase-beforeBase;

		System.out.printf( "Baseline (file read/write) time: %10.3f seconds\n", numSeconds(diffBase) );
		System.out.printf( "My Algorithm:  %10.3f seconds\n", numSeconds(diff1-diffBase) );
		System.out.printf( "Your Algorithm %10.3f seconds\n", numSeconds(diff2-diffBase) );
		System.out.printf( "Improvement ratio: %7.3f\n", (diff2-diffBase)*1.0/(diff1-diffBase) );

	}

	// the polynomial evaluation algorithm used by evalPolyTest1 (this is my algorithm)
	public static double evalPoly1( double coeffs[], int degree, double value )
	{
		double P = 0;
		for( int i = 0; i <= degree; i++ )
		{
			double term = coeffs[i];
			for( int j = 1; j <= i; j++ )
			{
				term = term * value;
			}
			P = P + term;

		}
		return P;
	}

	public static void evalPolyTest1( String infile, String outfile ) throws IOException
	{
		BufferedReader f = new BufferedReader( new FileReader( infile ) );
		PrintStream out = new PrintStream( new FileOutputStream( outfile ) );
		while ( f.ready() )
		{
			String coeffStr = f.readLine();
			String valueStr = f.readLine();
			StringTokenizer st1 = new StringTokenizer( coeffStr, " " );
			StringTokenizer st2 = new StringTokenizer( valueStr, " " );
			int coeffCount = st1.countTokens();
			int valueCount = st2.countTokens();
			double [] coeffs = new double[MAXDEG+1];
			double [] values = new double[MAXVALS];
			for( int i = 0; i < coeffCount; i ++ )
			{
			   coeffs[i] = Double.parseDouble( st1.nextToken() );
			}
			for( int i = 0; i < valueCount; i ++ )
			{
			   values[i] = Double.parseDouble( st2.nextToken() );
			}
			for( int i = 0; i < valueCount; i++ )
			{
				double ans = evalPoly1( coeffs, coeffCount-1, values[i] );
				out.printf( "%.5e", ans );
				out.println();
			}
		}
		out.close();
		f.close();
	}

	// baseLine simply reads the file but performs no computations
	public static void baseLine( String infile, String outfile ) throws IOException
	{
		BufferedReader f = new BufferedReader( new FileReader( infile ) );
		PrintStream out = new PrintStream( new FileOutputStream( outfile ) );
		while ( f.ready() )
		{
			String coeffStr = f.readLine();
			String valueStr = f.readLine();
			StringTokenizer st1 = new StringTokenizer( coeffStr, " " );
			StringTokenizer st2 = new StringTokenizer( valueStr, " " );
			int coeffCount = st1.countTokens();
			int valueCount = st2.countTokens();
			double [] coeffs = new double[MAXDEG+1];
			double [] values = new double[MAXVALS];
			for( int i = 0; i < coeffCount; i ++ )
			{
			   coeffs[i] = Double.parseDouble( st1.nextToken() );
			}
			for( int i = 0; i < valueCount; i ++ )
			{
			   values[i] = Double.parseDouble( st2.nextToken() );
			}
			for( int i = 0; i < valueCount; i++ )
			{
				double ans = 0;  // no computations made, assume output is zero
				out.printf( "%.5e", ans );
				out.println();
			}
		}
		out.close();
		f.close();
	}

	// your polynomial evaluation algorithm used by evalPolyTest2
	// to be filled up
	public static double evalPoly2( double coeffs[], int degree, double value )
	{
		double P = 0;
		// your algorithm here

		return P;
	}

	public static void evalPolyTest2( String infile, String outfile ) throws IOException
	{
		BufferedReader f = new BufferedReader( new FileReader( infile ) );
		PrintStream out = new PrintStream( new FileOutputStream( outfile ) );
		while ( f.ready() )
		{
			String coeffStr = f.readLine();
			String valueStr = f.readLine();
			StringTokenizer st1 = new StringTokenizer( coeffStr, " " );
			StringTokenizer st2 = new StringTokenizer( valueStr, " " );
			int coeffCount = st1.countTokens();
			int valueCount = st2.countTokens();
			double [] coeffs = new double[MAXDEG+1];
			double [] values = new double[MAXVALS];
			for( int i = 0; i < coeffCount; i ++ )
			{
			   coeffs[i] = Double.parseDouble( st1.nextToken() );
			}
			for( int i = 0; i < valueCount; i ++ )
			{
			   values[i] = Double.parseDouble( st2.nextToken() );
			}
			for( int i = 0; i < valueCount; i++ )
			{
				double ans = evalPoly2( coeffs, coeffCount-1, values[i] );
				out.printf( "%.5e", ans );
				out.println();
			}
		}
		out.close();
		f.close();
	}

	// number of milliseconds since some fixed checkpoint in the past
	public static long getTime()
	{
		return new Date().getTime();
	}

	// conversion from milliseconds to seconds
	public static double numSeconds( long milli )
	{
		return milli/1000.0;
	}


}
