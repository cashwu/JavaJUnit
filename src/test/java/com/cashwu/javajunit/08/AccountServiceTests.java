import com.cashwu.javajunit.account.Account;
import com.cashwu.javajunit.account.AccountManager;
import com.cashwu.javajunit.account.AccountService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTests {

    @Test
    void transferOK() {

        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        MockAccountManger mockAccountManger = new MockAccountManger();
        mockAccountManger.addAccount("1", senderAccount);
        mockAccountManger.addAccount("2", beneficiaryAccount);

        var accountService = new AccountService();
        accountService.setAccountManager(mockAccountManger);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    class MockAccountManger implements AccountManager {

        private Map<String, Account> accounts = new HashMap<>();

        @Override
        public Account findAccountForUser(String userId) {
            return this.accounts.get(userId);
        }

        @Override
        public void updateAccount(Account account) {
        }

        public void addAccount(String userId,
                               Account account) {

            this.accounts.put(userId, account);
        }
    }
}

