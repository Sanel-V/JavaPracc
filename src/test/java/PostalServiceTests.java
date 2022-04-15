import hu.elte.t8hxgr.post.Mail;
import hu.elte.t8hxgr.post.PostAccount;
import hu.elte.t8hxgr.post.PostOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PostalServiceTests
{
    public PostalServiceTests(){}

    @Test
    public void testMailNegativeAddressShouldThrowIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Mail(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 56})
    public void testMailNonNegativeAddress(int address)
    {
        Mail mail = new Mail(address);

        assertDoesNotThrow( () -> new Mail(address));
        assertEquals(address, mail.address);
    }

    @Test
    public void testPostAccountReceive()
    {
        PostAccount account = new PostAccount();
        Mail mail = new Mail(5);

        account.receive(mail);

        assertTrue(account.storedMail.contains(mail));

    }

    @Nested
    class PostOfficeTests
    {
        public PostOffice office;

        @BeforeEach
        public void initPostOffice()
        {
            office = new PostOffice();
        }

        public PostOfficeTests(){}

        @ParameterizedTest(name = "Value address: {0}")
        @ValueSource(ints = {2, 4, 6, 12})
        public void testPostOfficeSortEven(int even)
        {
            Mail mail = new Mail(even);

            office.sort(mail);


            assertAll(
                    //Do sanity check on parameter - param must be even
                    () -> assertEquals(0, even % 2),
                    () -> assertEquals(1, office.accountOne.storedMail.size()),
                    () -> assertTrue(office.accountOne.storedMail.contains(mail)),
                    () -> assertEquals(0, office.accountTwo.storedMail.size())
            );
        }

        @ParameterizedTest(name = "Value address: {0}")
        @ValueSource(ints = {1, 3, 3, 55})
        public void testPostOfficeSortOdd(int odd)
        {
            Mail mail = new Mail(odd);

            office.sort(mail);


            assertAll(
                    //Do sanity check on parameter - param must be even
                    () -> assertTrue(odd % 2 != 0),
                    //accountOne stores mail with even address, accountTwo with odd
                    () -> assertEquals(0, office.accountOne.storedMail.size()),
                    () -> assertEquals(1, office.accountTwo.storedMail.size()),
                    () -> assertTrue(office.accountTwo.storedMail.contains(mail))
            );
        }
    }

    @Nested
    public class PostOfficeTestsWithMocking
    {
        public PostOffice office;
        public PostAccount mockedAccountOne;
        public PostAccount mockedAccountTwo;
        public PostOfficeTestsWithMocking()
        {
        }

        @BeforeEach
        public void initPostOfficeWithMocking()
        {
            mockedAccountOne = Mockito.mock(PostAccount.class);
            mockedAccountTwo = Mockito.mock(PostAccount.class);

            office = new PostOffice(mockedAccountOne, mockedAccountTwo);
        }

        @ParameterizedTest(name = "Value address {0}")
        @ValueSource(ints = {0, 2, 4, 8})
        public void testPostOfficeSortEven(int even)
        {
            Mail mail = new Mail(even);

            office.sort(mail);

            assertEquals(0, even % 2);
            Mockito.verify(office.accountOne).receive(mail);
        }
        @ParameterizedTest(name = "One even ({0}), two odd ({1})")
        @CsvSource(value = {"2, 1, 3", "6, 43, 23"})
        public void testPostOfficeSortOneEvenTwoOdd(int even, int odd1, int odd2)
        {
            //GIVEN
            Mail mail = new Mail(even);
            Mail mailOdd1 = new Mail(odd1);
            Mail mailOdd2 = new Mail(odd2);
            InOrder order = Mockito.inOrder(mockedAccountTwo, mockedAccountOne);

            //WHEN
            office.sort(mailOdd1);
            office.sort(mail);
            office.sort(mailOdd2);

            //Sanity check
            //I should probably do these sanity checks after GIVEN, before WHEN
            assertAll(
                    () -> assertEquals(0, even % 2),
                    () -> assertEquals(1, odd1 % 2),
                    () -> assertEquals(1, odd2 % 2)
            );
            //THEN
            order.verify(mockedAccountTwo).receive(mailOdd1);
            order.verify(mockedAccountOne).receive(mail);
            order.verify(office.accountTwo).receive(mailOdd2);

        }
    }
}
