package org.fp024.mybatistest.board;

import org.fp024.mybatistest.test.DatabaseBeforeEachInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DatabaseBeforeEachInit.class)
class BoardServiceClientTest {
  @Test
  void testMain() {
    BoardServiceClient.main(null);
  }
}
