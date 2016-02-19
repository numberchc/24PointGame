package demo;

import javax.swing.JOptionPane;		//����ʱʹ��,��ʽ����ʱ����
import java.util.StringTokenizer;
import java.util.Arrays;

public class CalculateExpression {
	private String expression;
	private boolean isCorrect;
	private double result;
/** �ö���ӵ��3��˽�б���,�򾭳���ʹ��,���ڴ˶���
		expression,���ڼ�¼���ʽ
		isCorrect,��ʾ���ʽ�Ƿ�淶
		result,���ʽ������

	���,�ö���ӵ�����·������ⲿ����
		setExpression(String<���ʽ>)
			������ʽ,�޷���
		getExpression()
			���ر��ʽ,����ΪString
		isCorrect()
			���ر��ʽ�Ƿ�淶,����Ϊboolean
		isCorrect(int<���ָ���>)
			���ر��ʽ�Ƿ�淶,��ʱʽ�е����ָ���ҲΪ�ж�����,����Ϊboolean
		getNumbers()
			���ر��ʽ������,����Ϊint[]
		getResult()
			���ر��ʽ�ļ�����,����Ϊint
		getDoubleResult()
			���ر��ʽ�ļ�����,����Ϊdouble
		compareNumbers(int[]<��Աȵ�����>)
			�Ƚϱ��ʽ�е������Ƿ������������ͬ,��������Ϊboolean

	���,�ö���ӵ������˽�з���
		check()
			�жϱ��ʽ�Ƿ�淶,���Զ�����,��������Ϊboolean
			(�������ű������,����ʡ��)
		calStep1(),calStep2(),calStep3()
			������ʽ���������,�������;�Ϊdouble

*/

/** ���ڲ��Ե�main����,��ʽ����ʱ���� **
	public static void main(String[] args) {
		CalculateExpression n=new CalculateExpression();
		String s=JOptionPane.showInputDialog(null,"��������ʽ","Test2",JOptionPane.QUESTION_MESSAGE);
		if (s==null) {
			System.exit(0);
		}
		n.setExpression(s);
		//JOptionPane.showMessageDialog(null,n.getExpression(),"Test2",JOptionPane.INFORMATION_MESSAGE);
		if (!n.isCorrect())
		//if (!n.isCorrect(4))
			JOptionPane.showMessageDialog(null,"���ʽ����","Test2",JOptionPane.INFORMATION_MESSAGE);
		//int[] num=n.getNumbers();
		//JOptionPane.showMessageDialog(null,""+num[0]+","+num[1]+","+num[2]+","+num[3],"Test2",JOptionPane.INFORMATION_MESSAGE);
		//int result=n.getResult();
		double result=n.getDoubleResult();
		JOptionPane.showMessageDialog(null,"����ʽ�Ĵ�Ϊ"+result,"Test2",JOptionPane.INFORMATION_MESSAGE);
	}
*/
/** ��ʼ������CalculateExpression() */
	public CalculateExpression() {
		expression="";
		isCorrect=false;
	}

/** setExpression(String<���ʽ>),������ʽ */
	public void setExpression(String input) {
		expression=input;
		isCorrect=check();
		//JOptionPane.showMessageDialog(null,expression,"Test2",JOptionPane.INFORMATION_MESSAGE);
		if (isCorrect) {
			result=calStep1();
		} else
			result=0;
	}

/** getExpression(),������ʽ */
	public String getExpression() {
		return expression;
	}

/** check(),�жϱ��ʽ�淶���,���Զ����� */
	private boolean check() {
		char t,tt=expression.charAt(0);
		int sum=0,add=0;		//������������,��������λ��
		boolean hasNum=false;
		StringBuffer s=new StringBuffer(expression);
		if (tt=='+' || tt=='-' || tt=='.') {
			s.insert(0,'0');
			add++;
		} else
		if (tt==')' || tt=='*' || tt=='/') {
			return false;
		}
		for (int i=0; i<expression.length(); i++) {
			t=tt;
			if (i!=expression.length()-1) {
				tt=expression.charAt(i+1);
			} else tt='E';
			if (t>='0' && t<='9') {
				hasNum=true;
				if (tt=='(') {
					s.insert(i+add+1,'*');
					add++;
				}
			} else
			if (t=='(') {
				sum++;
				if (tt=='.' || tt=='+' || tt=='-') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t==')') {
				sum--;
				if (tt>='0' && tt<='9') {
					s.insert(i+add+1,'*');
					add++;
				} else
				if (tt=='.') {
					return false;
				}
			} else
			if (t=='+') {
				if (tt=='+' || tt=='-') {
					s.deleteCharAt(i+add);
					add--;
				} else
				if (tt=='.') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t=='-') {
				if (tt=='+') {
					s.replace(i+add,i+add+2,"-");
					add--;
				} else
				if (tt=='-') {
					s.replace(i+add,i+add+2,"+");
					add--;
				} else
				if (tt=='.') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t=='*' || t=='/') {
				if (tt==')' || tt=='+' || tt=='-' || tt=='*' || tt=='/') {
					return false;
				}
			} else
			if (t=='.') {
				if (tt=='.') {
					return false;
				} else
				if (tt<'0' || tt>'9') {
					s.insert(i+add+1,'0');
					add++;
				}
			} else return false;
		} 
		if (sum==0 && hasNum && (expression.indexOf('(')<=expression.indexOf(')'))) {
			expression=s.substring(0);
			return true;
		} else
		return false;
	}

/** isCorrect(),���ر��ʽ�Ƿ�淶 */
	public boolean isCorrect() {
		return isCorrect;
	}

/** isCorrect(int<���ָ���>),���ر��ʽ�Ƿ�淶 */
	public boolean isCorrect(int n) {
		if (isCorrect&&this.getNumbers().length==n) {
			return true;
		}
		return false;
	}

/** getNumbers(),���ر��ʽ������ */
	public int[] getNumbers() {
		if (isCorrect) {
			StringTokenizer t=new StringTokenizer(expression,"+-*/()");
			int[] n=new int[t.countTokens()];
			for (int i=0; i<n.length; i++) {	//����ʹ��t.countTokens()��������,ԭ��̫�˽�
				n[i]=Integer.parseInt(t.nextToken());
			}
			return n;
		}
		return new int[]{0};
	}

/** getDoubleNumbers(),���ر��ʽ������ */
	public double[] getDoubleNumbers() {
		if (isCorrect) {
			StringTokenizer t=new StringTokenizer(expression,"+-*/()");
			double[] n=new double[t.countTokens()];
			for (int i=0; i<n.length; i++) {	//����ʹ��t.countTokens()��������,ԭ��̫�˽�
				n[i]=Double.parseDouble(t.nextToken());
			}
			return n;
		}
		return new double[]{0};
	}

/** getResult(),������ʽ�Ľ��,����int���� */
	public int getResult() {
		if (isCorrect) {
			return (int)result;
		}
		return 0;
	}

/** getDoubleResult(),������ʽ�Ľ��,����double���� */
	public double getDoubleResult() {
		if (isCorrect) {
			return result;
		}
		return 0;
	}

/** calStep1(),������ʽ�ĵ�һ��,�������� */
	private double calStep1() {
		int startIndex,endIndex;
		StringBuffer s=new StringBuffer(expression);
		while (s.substring(0).indexOf('(')!=-1) {
			endIndex=s.substring(0).indexOf(')');
			startIndex=s.substring(0).lastIndexOf('(',endIndex);
			s.replace(startIndex,endIndex+1,""+calStep2(s.substring(startIndex+1,endIndex)));
			if (!isCorrect) {
				return 0;
			}
		}
		return calStep2(s.substring(0));
	}

/** calStep2(),������ʽ�ĵڶ���,�ȳ˳���Ӽ� */
	private double calStep2(String expression2) {
		boolean isOdd=true;
		StringTokenizer s=new StringTokenizer(expression2,"+-",true);
		expression2="";
		while (s.hasMoreTokens()) {
			if (isOdd) {
				expression2+=(""+calStep3(s.nextToken()));
				isOdd=false;
			}
			else {
				expression2+=s.nextToken();
				isOdd=true;
			}
			if (!isCorrect) {
				return 0;
			}
		}
		return calStep3(expression2);
	}

/** calStep3(),������ʽ�ĵ�����,Ҳ�������һ��,����Ӽ��˳� */
	private double calStep3(String expression2) {
		StringTokenizer s=new StringTokenizer(expression2,"+-*/",true);
		double n=Double.parseDouble(s.nextToken());
		double nTmp;
		char operator;
		while (s.hasMoreTokens()) {
			operator=s.nextToken().charAt(0);
			nTmp=Double.parseDouble(s.nextToken());
			switch (operator) {
				case '+' :
					n+=nTmp;break;
				case '-' :
					n-=nTmp;break;
				case '*' :
					n*=nTmp;break;
				default :
					if (nTmp==0) {
						isCorrect=false;
						return 0;
					} else
						n/=nTmp;
			}
		}
		return n;
	}

/** compareNumbers(int[]<��Աȵ�����>),�Ƚϱ��ʽ�е������Ƿ������������ͬ */
	public boolean compareNumbers(int[] n) {
		if (isCorrect) {
			int[] m=getNumbers();
			if (m.length==n.length) {
				java.util.Arrays.sort(n);
				java.util.Arrays.sort(m);
				for (int i=0; i<n.length; i++) {
					if (n[i]!=m[i]) {
						return false;
					}
				}
				return true;
			} else
				return false;
		}
		return false;
	}
}
