package Chapter_3;

public class Classser {
	public static void main(String[] args) {
		int n=5;
		char ch = 'A';
		for ( int i=1 ; i<=6 ; i++) {
			for ( int j=1 ; j<=n ; j++) {
				System.out.printf("%c ", ch);
				if (ch < 'Z' )
					ch++;
				if ( ch == 'Z')
					break;
			}
			System.out.println("");
		}
		
		
	}
}
