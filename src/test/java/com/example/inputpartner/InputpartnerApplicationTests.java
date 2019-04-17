package com.example.inputpartner;

import com.example.inputpartner.entity.ExternalTransaction;
import com.example.inputpartner.entity.OrderStatus;
import com.example.inputpartner.entity.TransactionStatus;
import com.example.inputpartner.entity.WithdrawalOrder;
import com.example.inputpartner.repository.ExternalTransactionRepository;
import com.example.inputpartner.repository.WithdrawalOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputpartnerApplicationTests {

	@Autowired
	private WithdrawalOrderRepository withdrawalOrderRepository;

	@Autowired
	private ExternalTransactionRepository transactionRepository;

	@Test
	public void contextLoads() {
		ExternalTransaction externalTransaction = new ExternalTransaction();
		externalTransaction.setTransactionId("123");
		externalTransaction.setAmount(BigInteger.TEN);
		externalTransaction.setStatus(TransactionStatus.PENDING);
		externalTransaction.setCreatedTimestamp(System.currentTimeMillis());
		externalTransaction.setDestinationAddress("some address");
		transactionRepository.save(externalTransaction);
	}

	@Test
	public void testFindById() {
		Optional<ExternalTransaction> byId = transactionRepository.findById("123");
		System.out.println(byId.get());
	}

	@Test
	public void testSaveWithdrawalOrder() {
		ExternalTransaction externalTransaction = new ExternalTransaction();
		externalTransaction.setTransactionId("124");
		externalTransaction.setAmount(BigInteger.TEN);
		externalTransaction.setStatus(TransactionStatus.PENDING);
		externalTransaction.setCreatedTimestamp(System.currentTimeMillis());
		externalTransaction.setDestinationAddress("some address");

		WithdrawalOrder order = new WithdrawalOrder();
		order.setAmount(BigInteger.TEN);
		order.setDestinationAddress("address");
		order.setExternalTransactionAttemptCount(2);
		order.setStatus(OrderStatus.COIN_SENT);
		order.setCreatedTimestamp(System.currentTimeMillis());
		Set<ExternalTransaction> transactionSet = new HashSet<>();
		transactionSet.add(externalTransaction);
		order.setExternalTransactions(transactionSet);
		withdrawalOrderRepository.save(order);
	}

	@Test
	public void test() {
		Optional<WithdrawalOrder> byId = withdrawalOrderRepository.findById(2L);
		System.out.println(byId.get());
	}

}
