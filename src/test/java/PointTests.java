import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import hu.elte.t8hxgr.Point;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PointTests
{
    Point o;
    Point p;
    PointTests()
    {

    }

    @BeforeEach
    public void initPoints()
    {
        o = new Point();
        p = new Point(4,5);
    }

    @ParameterizedTest(name = "Translate by: ({0}, {1})")
    @CsvSource(value = {"4, 5", "1, 2", "0, 0", "-2, 5", "3, -1", "-6, -6"})
    public void TestPointTranslateTwoParam(int tx, int ty)
    {

        Point t;

        t = p.translate(tx, ty);

        assertAll(
                () -> assertEquals(tx, (t.x - p.x)),
                () -> assertEquals(ty, (t.y - p.y))
        );
    }

    @ParameterizedTest(name = "Translate by: ({0}, {0})")
    @CsvSource(value = {"4", "1", "0", "-2"})
    public void TestPointTranslateOneParam(int txy)
    {

        Point t;

        t = p.translate(txy);

        assertAll(
                () -> assertEquals(txy, (t.x - p.x)),
                () -> assertEquals(txy, (t.y - p.y))
        );
    }

    @ParameterizedTest(name = "Translate by: ({0}, {1})")
    @CsvFileSource(files = "./src/test/resources/points.csv")
    public void testOriginTranslate(int tx, int ty)
    {

        Point t;

        t = p.translate(tx, ty);

        assertAll(
                () -> assertEquals(tx, (t.x - p.x)),
                () -> assertEquals(ty, (t.y - p.y))
        );
    }

}
