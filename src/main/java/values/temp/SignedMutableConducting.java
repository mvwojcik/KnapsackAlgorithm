/*
 * Copyright (c) 1999, 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package values.temp;

/**
 * A class used to represent multiprecision integers that makes efficient
 * use of allocated space by allowing a number to occupy only part of
 * an array so that the arrays do not have to be reallocated as often.
 * When performing an operation with many iterations the array used to
 * hold a number is only increased when necessary and does not have to
 * be the same size as the number it represents. A mutable number allows
 * calculations to occur on the same number without having to create
 * a new number for every step of the calculation as occurs with
 * Conductings.
 *
 * Note that SignedMutableConductings only support signed addition and
 * subtraction. All other operations occur as with MutableConductings.
 *
 * @see     Conducting
 * @author  Michael McCloskey
 * @since   1.3
 */

class SignedMutableConducting extends MutableConducting {

   /**
     * The sign of this MutableConducting.
     */
    int sign = 1;

    // Constructors

    /**
     * The default constructor. An empty MutableConducting is created with
     * a one word capacity.
     */
    SignedMutableConducting() {
        super();
    }

    /**
     * Construct a new MutableConducting with a magnitude specified by
     * the int val.
     */
    SignedMutableConducting(int val) {
        super(val);
    }

    /**
     * Construct a new MutableConducting with a magnitude equal to the
     * specified MutableConducting.
     */
    SignedMutableConducting(MutableConducting val) {
        super(val);
    }

   // Arithmetic Operations

   /**
     * Signed addition built upon unsigned add and subtract.
     */
    void signedAdd(SignedMutableConducting addend) {
        if (sign == addend.sign)
            add(addend);
        else
            sign = sign * subtract(addend);

    }

   /**
     * Signed addition built upon unsigned add and subtract.
     */
    void signedAdd(MutableConducting addend) {
        if (sign == 1)
            add(addend);
        else
            sign = sign * subtract(addend);

    }

   /**
     * Signed subtraction built upon unsigned add and subtract.
     */
    void signedSubtract(SignedMutableConducting addend) {
        if (sign == addend.sign)
            sign = sign * subtract(addend);
        else
            add(addend);

    }

   /**
     * Signed subtraction built upon unsigned add and subtract.
     */
    void signedSubtract(MutableConducting addend) {
        if (sign == 1)
            sign = sign * subtract(addend);
        else
            add(addend);
        if (intLen == 0)
             sign = 1;
    }

    /**
     * Print out the first intLen ints of this MutableConducting's value
     * array starting at offset.
     */
    public String toString() {
        return this.toConducting(sign).toString();
    }

}
