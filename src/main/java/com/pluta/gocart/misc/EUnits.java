package com.pluta.gocart.misc;

public enum EUnits {
    KG {
        @Override
        public java.lang.String toString() {
            return "Kilo";
        }
    },
    L {
        @Override
        public java.lang.String toString() {
            return "Litrów";
        }
    },
    G {
        @Override
        public String toString() {
            return "Gramów";
        }
    },
    I {
        @Override
        public String toString() {
            return "Sztuk";
        }
    }

}
