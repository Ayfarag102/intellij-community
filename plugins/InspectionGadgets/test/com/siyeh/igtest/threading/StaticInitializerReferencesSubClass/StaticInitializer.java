class Super {
  static Super C1 = new <warning descr="Referencing subclass Sub from superclass Super initializer might lead to class loading deadlock">Sub</warning>();
  static Object C2 = <warning descr="Referencing subclass Sub from superclass Super initializer might lead to class loading deadlock">Sub</warning>.create();
  static final Sub C3;
  static final Sub C4 = SubFactory.<warning descr="Referencing subclass Sub from superclass Super initializer might lead to class loading deadlock">create</warning>();
  static final String C5 = SubFactory.<warning descr="Referencing subclass Sub from superclass Super initializer might lead to class loading deadlock">create</warning>().toString();

  static Object OK_INSIDE_ANONYMOUS = new Object() {{ Sub s = new Sub(); }};
  static Object OK_UNRELATED = "abc";
  static Super OK_SAME = new Super();
  static Super OK_SAME_ANONYMOUS = new Super(){};
  static Sub[] OK_ARRAY = new Sub[3];
  static java.util.List<Sub> OK_GENERICS = new java.util.ArrayList<Sub>();

  static {
    C3 = new <warning descr="Referencing subclass Sub from superclass Super initializer might lead to class loading deadlock">Sub</warning>();
  }
}

class Sub extends Super implements Intf {
  static native Object create();
}

class SubFactory {
  static native Sub create();

}

interface Intf {
  Sub ok = new Sub();
}