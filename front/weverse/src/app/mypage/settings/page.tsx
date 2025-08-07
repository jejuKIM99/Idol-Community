'use client';

import { useState } from 'react';
import styles from './settings.module.css';
import { FiChevronRight } from 'react-icons/fi';
import { FaGoogle, FaTwitter, FaApple } from 'react-icons/fa';

// 커스텀 토글 스위치 컴포넌트
const ToggleSwitch = ({ id, checked, onChange }: { id: string, checked: boolean, onChange: (e: React.ChangeEvent<HTMLInputElement>) => void }) => (
    <label htmlFor={id} className={styles.toggleSwitch}>
        <input type="checkbox" id={id} checked={checked} onChange={onChange} />
        <span className={styles.slider}></span>
    </label>
);

export default function SettingsPage() {
    const [notifications, setNotifications] = useState({
        promotional: false,
        night: false,
        personalizedAds: false,
    });

    const handleToggle = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, checked } = e.target;
        setNotifications(prev => ({ ...prev, [id]: checked }));
    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                {/* 내 정보 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>내 정보</h2>
                    <div className={styles.infoGrid}>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>이메일</span>
                            <span className={styles.infoValue}>s20***@naver.com</span>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>닉네임</span>
                            <span className={styles.infoValue}>Member_2svqgq</span>
                            <button className={styles.changeButton}>변경</button>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>이름</span>
                            <span className={styles.infoValue}>N*w****r</span>
                            <button className={styles.changeButton}>변경</button>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>비밀번호</span>
                            <span className={styles.infoValue}>●●●●●●●●</span>
                            <button className={styles.changeButton}>변경</button>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>국가</span>
                            <span className={styles.infoValue}>대한민국</span>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>SMS 인증</span>
                            <span className={styles.infoValue}>sms 인증 정보가 없습니다.</span>
                        </div>
                    </div>
                </section>

                {/* 연결된 SNS 계정 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>연결된 SNS 계정</h2>
                    <div className={styles.snsList}>
                        <div className={styles.snsItem}>
                            <FaGoogle size={20} /><span>구글</span><button className={styles.connectButton}>연결하기</button>
                        </div>
                        <div className={styles.snsItem}>
                            <FaTwitter size={20} /><span>트위터</span><button className={styles.connectButton}>연결하기</button>
                        </div>
                        <div className={styles.snsItem}>
                            <FaApple size={20} /><span>애플</span><button className={styles.connectButton}>연결하기</button>
                        </div>
                    </div>
                </section>

                {/* 연결된 서비스 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>연결된 서비스</h2>
                    <div className={styles.serviceItem}>
                        <img src="https://weverse.io/favicon.ico" alt="Weverse logo" />
                        <div>
                            <p className={styles.serviceName}>Weverse</p>
                            <p className={styles.serviceDate}>가입일 2025.07.30</p>
                        </div>
                        <button className={styles.serviceButton}>서비스 탈퇴</button>
                    </div>
                </section>

                {/* 이벤트/혜택 알림 설정 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>이벤트 • 혜택 알림 설정</h2>
                    <div className={styles.settingRow}>
                        <div>
                            <p className={styles.settingLabel}>광고성 정보 알림 받기</p>
                            <p className={styles.settingDesc}>아티스트의 최신 소식과 이벤트 정보부터 위버스 공식 상품에 대한 소식까지 알림으로 빠르게 받아보세요.</p>
                        </div>
                        <ToggleSwitch id="promotional" checked={notifications.promotional} onChange={handleToggle} />
                    </div>
                    <div className={styles.settingRow}>
                        <div>
                            <p className={styles.settingLabel}>야간 알림 받기</p>
                            <p className={styles.settingDesc}>오후 9시-오전 8시(KST)에도 아티스트의 중요한 이벤트와 혜택 알림을 놓치지 않고 받을 수 있어요.</p>
                        </div>
                        <ToggleSwitch id="night" checked={notifications.night} onChange={handleToggle} />
                    </div>
                </section>

                {/* 맞춤형 광고 및 서비스 보기 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>맞춤형 광고 및 서비스 보기</h2>
                    <div className={styles.settingRow}>
                        <div>
                            <p className={styles.settingLabel}>맞춤형 광고 보기</p>
                            <p className={styles.settingDesc}>나의 관심사와 연관된 맞춤형 광고를 우선적으로 제공해 드려요.</p>
                        </div>
                        <ToggleSwitch id="personalizedAds" checked={notifications.personalizedAds} onChange={handleToggle} />
                    </div>
                </section>

                {/* 약관 및 정책 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>약관 및 정책</h2>
                    <div className={styles.policyList}>
                        <a href="#" className={styles.policyItem}>개인정보처리방침 <FiChevronRight /></a>
                        <a href="#" className={styles.policyItem}>이용약관 <FiChevronRight /></a>
                    </div>
                </section>

                <div className={styles.deleteAccount}>
                    <a href="#">위버스 계정 탈퇴하기</a>
                </div>
            </div>
        </div>
    );
}
