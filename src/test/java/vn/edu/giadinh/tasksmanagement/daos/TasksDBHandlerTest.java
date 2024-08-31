package vn.edu.giadinh.tasksmanagement.daos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TasksDBHandlerTest {
    TaskDBHandler taskDBHandler = TaskDBHandler.getInstance();

    @Test
    void search() {
        try {
            taskDBHandler.search("4")
                    .forEach(
                            System.out::println
                    );

            System.out.println();

            taskDBHandler.search("dữ")
                    .forEach(
                            System.out::println
                    );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exists() {
        try {
            System.out.println(taskDBHandler.exists(4));
            System.out.println(taskDBHandler.exists(3));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByResponsibility() {
        try {
            taskDBHandler.getByResponsibility("nguyenhoangkhuyen")
                    .forEach(System.out::println);

            System.out.println();

            taskDBHandler.getByResponsibility("nguyenthithanhhieu")
                    .forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByTester() {
        try {
            taskDBHandler.getByTester("dinhngocminhduy")
                    .forEach(System.out::println);

            System.out.println();

            taskDBHandler.getByTester("luongminhhau")
                    .forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByUser() {
        try {
            taskDBHandler.getByUser("nguyenhoangkhuyen")
                    .forEach(System.out::println);

            System.out.println();

            taskDBHandler.getByUser("nguyenthithanhhieu")
                    .forEach(System.out::println);

            System.out.println();

            taskDBHandler.getByUser("dinhngocminhduy")
                    .forEach(System.out::println);

            System.out.println();

            taskDBHandler.getByUser("luongminhhau")
                    .forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
