package polynomial;

/**
 * Polynomial
 *
 * @author mike
 * @version 
 */
public class Polynomial {
    
    private double[] poly;
    
    /**
     * Constructs an object of type Polynomial.
     * @param degree degree of Polynomial
     */
    public Polynomial(int degree) {
    poly = new double[degree +1];
    }
    
    /**
     * 
     * Constructs an object of type Polynomial.
     * @param args the coefficients starting at 0.
     */
    public Polynomial(double...args) {
        this.poly=args;
        for(int i=0; i < args.length; i++) {
            this.setCoefficient(i, args[i]);
        }
    }
    
    /**
     * Sets the specified term to the specified coefficient.
     * @param exponent term to set.
     * @param coefficient coefficient to set.
     */
    public void setCoefficient(int exponent, double coefficient) {
        if (exponent >= 0 && exponent <= poly.length) {
        poly[exponent]= coefficient;
        } 
    }
    
    /**
     * Returns the coefficient of the term with the specified exponent.
     * @param exponent exponent of term to return
     * @return the coefficient of the specified term
     */
    public double getCoefficient(int exponent){
        return poly[exponent];
    }


    /**
     * Returns the degree for this Polynomial.
     * @return the degree
     */
    public int getDegree() {
         int degree=0;
         for(int i = 0; i < poly.length; i++) {
             if (poly[i] != 0) {
                 degree = i;
             }
         }
         return degree;
    }
    
    /**
     * Evaluates the polynomial at specified x-value.
     * @param x value of x
     * @return the polynomial evaluated at x
     */
    public double evaluate(double x) {
        double sum = 0;
        for(int i = 0; i < poly.length; i++) {
            sum += poly[i] * Math.pow(x, i);
        }
        return sum;
    }
    
    /**
     * Returns the sum of this polynomial and the argument polynomial.
     * @param operand polynomial to add to this
     * @return the sum
     */
    public Polynomial add(Polynomial operand) {
        Polynomial sum = new Polynomial(Math.max(this.getDegree(), operand.getDegree()));
        for(int i=0; i <= Math.min(this.getDegree(), operand.getDegree()); i++) {
            sum.setCoefficient(i,operand.getCoefficient(i) + this.getCoefficient(i));
        }
        if(this.getDegree() > operand.getDegree()) {
        for(int i = operand.getDegree() + 1; i <= this.getDegree(); i++) {
            sum.setCoefficient(i, this.getCoefficient(i));
        }}else if (operand.getDegree() > this.getDegree()) {
            for(int i = this.getDegree() +1; i <= operand.getDegree();i++ ) {
                sum.setCoefficient(i, operand.getCoefficient(i));
            }
        }
        
                
        return sum;
    }
    
    private Polynomial subtractwith0 (Polynomial operand) {
      
        
        Polynomial difference = new Polynomial(Math.max(this.getLength(), operand.getDegree()));
        for(int i=0; i <= Math.min(this.getDegree(), operand.getDegree()); i++) {
            difference.setCoefficient(i,this.getCoefficient(i) - operand.getCoefficient(i));
        }
        if(this.getDegree() > operand.getDegree()) {
        for(int i = operand.getDegree() + 1; i <= this.getDegree(); i++) {
            difference.setCoefficient(i, this.getCoefficient(i));
        }}else if (operand.getDegree() > this.getDegree()) {
            for(int i = this.getDegree() +1; i <= operand.getDegree();i++ ) {
                difference.setCoefficient(i, -operand.getCoefficient(i));
            }
        }
        return difference;
    }
    /**
     * Returns the difference of this polynomial and the argument polynomial
     * @param operand polynomial to subtract from this
     * @return the difference
     */
 public Polynomial subtract (Polynomial operand) {
      
        
        Polynomial difference = new Polynomial(Math.max(this.getDegree(), operand.getDegree()));
        for(int i=0; i <= Math.min(this.getDegree(), operand.getDegree()); i++) {
            difference.setCoefficient(i,this.getCoefficient(i) - operand.getCoefficient(i));
        }
        if(this.getDegree() > operand.getDegree()) {
        for(int i = operand.getDegree() + 1; i <= this.getDegree(); i++) {
            difference.setCoefficient(i, this.getCoefficient(i));
        }}else if (operand.getDegree() > this.getDegree()) {
            for(int i = this.getDegree() +1; i <= operand.getDegree();i++ ) {
                difference.setCoefficient(i, -operand.getCoefficient(i));
            }
        }
        return difference;
    }
 
    /**
    * Returns this polynomial multiplied by the specified constant.
    * @param constant constant to multiply
    * @return the product
    */
    public Polynomial constantMultiply(double constant) {
        Polynomial product = new Polynomial(this.getDegree());
        for(int i=0; i <= this.getDegree(); i++) {
            product.setCoefficient(i,this.getCoefficient(i) * constant);
        }
        return product;
    }
    
    /**
     * Returns the product of this polynomial and the argument polynomial
     * @param operand polynomial to multiply this by
     * @return the product
     */
    public Polynomial multiply(Polynomial operand) {
        Polynomial product = new Polynomial(this.getDegree() + operand.getDegree());
        for (int j = 0; j <= this.getDegree(); j++) {
            Polynomial subproduct = new Polynomial (operand.getDegree()+j);
        for(int i = 0; i <= operand.getDegree(); i++) {
            subproduct.setCoefficient(j + i,this.getCoefficient(j)*operand.getCoefficient(i));
        }
        for(int k = j; k <= subproduct.getDegree(); k++) {
        product.setCoefficient(k,product.getCoefficient(k) + subproduct.getCoefficient(k));
        }
        }
        return product;
    }
    
    /**
     * Returns the n-th derivative of this Polynomial where n = number
     * @param number the derivative to take
     * @return the derivative
     */
    public Polynomial derivative(int number) {
        if(number > 0) {
            if(number==1) {
                Polynomial derivative = new Polynomial(this.getDegree()-1);
                for (int i=1; i <= this.getDegree(); i++) {
                    derivative.setCoefficient(i-1, this.getCoefficient(i)*i);
                }
                return derivative;
            }else { 
                return derivative(number-1).derivative(1);
            }
        }else return this;
    }
    
    /**
     * Returns the indefinite Integral of this polynomial, leaving out the unknown constant term.
     * @return the indefinite Integral
     */
    public Polynomial indefiniteIntegral() {
                Polynomial integral = new Polynomial(this.getDegree()+1); 
                    for (int i=0; i <= this.getDegree(); i++) {
                        integral.setCoefficient(i+1, this.getCoefficient(i) * 1/(i+1));
                    }
                    return integral;
    }
    
    /**
     * Returns the definite integral of this polynomial evaluated over the specified range.
     * @param first upper bound of integral.
     * @param second lower bound of integral.
     * @return 
     */
    public double definiteIntegral(double first, double second) {
        return this.indefiniteIntegral().evaluate(first) - this.indefiniteIntegral().evaluate(second);
    }
    
    /**
     * Returns an array of 2 Polynomials, at 0 index is the quotient
     * and at the 1 index is the remainder when this Polynomial is
     * divided by the argument Polynomial.
     * @param divisor Polynomial to divide this Polynomial by
     * @return the quotient and remainder
     */
    public Polynomial[] divide(Polynomial divisor) {
        Polynomial[] quotientAndRemainder=new Polynomial[2];
        Polynomial quotient;
        Polynomial remainder;
        if(divisor.getDegree() > this.getDegree()) {
          quotient=new Polynomial(0);
          quotient.setCoefficient(0,0);
           remainder = this;
         quotientAndRemainder[0]=quotient;
        quotientAndRemainder[1]=remainder;    
       }else if(this.getDegree()==0) {
           
       quotient=new Polynomial(this.getCoefficient(0) / divisor.getCoefficient(0));
       remainder = new Polynomial(0.0);
       quotientAndRemainder[0]=quotient;
       quotientAndRemainder[1]=remainder;
        }else {
            Polynomial[] sbd=this.subdivision(divisor);
            quotient=new Polynomial(this.getDegree()-divisor.getDegree());
            quotient.setCoefficient(this.getDegree()-divisor.getDegree(), 
                    sbd[0].getCoefficient(this.getDegree()-divisor.getDegree()));
            int i=1;
            while(sbd[1].getDegree()>=divisor.getDegree()&&i<=quotient.getDegree()) {
                
                sbd=sbd[1].subdivision(divisor);
                quotient.setCoefficient(quotient.getDegree()-i, 
                        sbd[0].getCoefficient(quotient.getDegree()-i));
                i++;
            }
             remainder = sbd[2];
             quotientAndRemainder[0] = quotient;
             quotientAndRemainder[1] = remainder;
       }
        return quotientAndRemainder;
        
    }
    
    private Polynomial[] subdivision(Polynomial divisor) {
        Polynomial[] subdivisors = new Polynomial[3];
        Polynomial quotient = new Polynomial(this.getLength()-divisor.getDegree());
        Polynomial subdivisor = new Polynomial(this.getLength());
        quotient.setCoefficient(this.getLength() - divisor.getDegree(), 
                this.getCoefficient(this.getLength()) / 
                divisor.getCoefficient(divisor.getDegree()));
        Polynomial divisor2 = new Polynomial(this.getLength());
        for (int j=0; j<=divisor.getDegree(); j++) {
            divisor2.setCoefficient(divisor.getDegree()-j, 
                    divisor.getCoefficient(divisor.getDegree()-j));
        }
        for(int i=0; i<=(divisor2.getLength());i++) {
               
                if(i > divisor.getDegree()) {
                    subdivisor.setCoefficient(this.getLength()-i,
                            0);
                }else {
                subdivisor.setCoefficient(this.getLength()-i, 
                        this.getCoefficient(this.getLength())* 
                                divisor.getCoefficient(divisor.getDegree()-i));
                }
            
            } 
        Polynomial subdivisor2 = this.subtractwith0(subdivisor);
        Polynomial subdivisor3 = new Polynomial(subdivisor2.getLength()-1);
        for(int p=0; p <= subdivisor3.getLength(); p++) {
            subdivisor3.setCoefficient(p,subdivisor2.getCoefficient(p));
        }
        subdivisors[0]=quotient;
        subdivisors[1]=subdivisor3;
        subdivisors[2]=subdivisor2;
        return subdivisors;
    }
    
    private int getLength() {
        return poly.length -1;
    }
    
    /**
     * Returns the roots of this Polynomial in an array.
     * @return the roots
     */
    public double[] roots() {
        double roots[];
       
             roots = new double[this.getDegree()];
             roots[0]= this.oneRoot();
             Polynomial rootRemoved = new Polynomial(this.getDegree());
             for(int i = 0; i <= this.getDegree(); i++) {
                 rootRemoved.setCoefficient(i, this.getCoefficient(i));
             }
             
             for (int j = 1; j < this.getDegree(); j++) {
                 Polynomial linear = new Polynomial(-roots[j-1], 1);
                 rootRemoved = rootRemoved.divide(linear)[0];
                 roots[j]= rootRemoved.oneRoot();
             }
             
             
        
        double tolerance=0.00000001;
        for(int p=0; p < roots.length; p++) {
            if(Math.abs(this.evaluate(roots[p]))>tolerance) {
                roots[p]= Double.NaN;
            }
            
        }
        return roots;
                
    }
    
    private double oneRoot() {
        double guess=this.getCoefficient(0);
        for (int i=0; i<500; i++){
            if(this.derivative(1).evaluate(guess)!=0) {
            guess=guess - this.evaluate(guess)/this.derivative(1).evaluate(guess);
            }
        }
        return guess;
    }
    
    /**
     * Returns a String description of this Polynomial.
     * @see java.lang.Object#toString()
     * @return string description
     */
    public String toString() {
        String info = this.getCoefficient(this.getDegree()) + "x^"
                + (int)this.getDegree();
        for(int i = 1; i <= this.getDegree(); i++) {
            if(this.getCoefficient(this.getDegree()-i)!=0 && (this.getDegree()!=i)) {
            info = info + " + "  + this.getCoefficient(this.getDegree()-i)
            + "x^"+ ((int)this.getDegree()-i);
            }
            else if(this.getCoefficient(this.getDegree()-i)!=0) {
                info += " + " + this.getCoefficient(this.getDegree()-i);
            }
        }
        return info;
             
    }
    
   
}
