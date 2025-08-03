'use client';

import styles from './orders.module.css';
import { FiAlertCircle } from 'react-icons/fi';

export default function OrdersPage() {
  // 현재는 주문 내역이 없는 상태만 보여줍니다.
  const hasOrders = false;

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>주문내역</h1>
      <div className={styles.content}>
        {hasOrders ? (
          <div>{/* 주문 내역 목록이 여기에 표시됩니다. */}</div>
        ) : (
          <div className={styles.emptyState}>
            <FiAlertCircle size={40} className={styles.icon} />
            <p>주문 내역이 없습니다.</p>
          </div>
        )}
      </div>
    </div>
  );
}
