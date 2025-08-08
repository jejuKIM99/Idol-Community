'use client';

import { useState, useEffect } from 'react';
import { useAuth } from '../../context/AuthContext';
import styles from './settings.module.css';
import { FiChevronRight } from 'react-icons/fi';
import { FaGoogle, FaTwitter, FaApple } from 'react-icons/fa';

// 커스텀 토글 스위치 컴포넌트 (기존 코드 유지)
const ToggleSwitch = ({ id, checked, onChange }: { id: string, checked: boolean, onChange: (e: React.ChangeEvent<HTMLInputElement>) => void }) => (
    <label htmlFor={id} className={styles.toggleSwitch}>
        <input type="checkbox" id={id} checked={checked} onChange={onChange} />
        <span className={styles.slider}></span>
    </label>
);

export default function SettingsPage() {
    const { token, isAuthenticated, isLoading: isAuthLoading } = useAuth();
    const [userData, setUserData] = useState<any>(null);
    const [loading, setLoading] = useState(true);
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    // 인라인 편집을 위한 상태
    const [isEditingNickname, setIsEditingNickname] = useState(false);
    const [newNickname, setNewNickname] = useState('');
    const [isEditingName, setIsEditingName] = useState(false);
    const [newName, setNewName] = useState('');

    const [notifications, setNotifications] = useState({
        promotional: false,
        night: false,
        personalizedAds: false,
    });

    const handleToggle = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, checked } = e.target;
        setNotifications(prev => ({ ...prev, [id]: checked }));
    };

    useEffect(() => {
        if (isAuthLoading) {
            return; // AuthContext 로딩 완료 대기
        }

        if (!isAuthenticated || !token) {
            setLoading(false);
            setError('로그인이 필요합니다.');
            return;
        }

        const fetchUserData = async () => {
            try {
                const response = await fetch('http://localhost:80/api/user/me', {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setUserData(data);
                    setNewNickname(data.nickname || '');
                    setNewName(data.name || '');
                } else {
                    const errorText = await response.text();
                    setError(`사용자 정보를 불러오는데 실패했습니다: ${errorText}`);
                }
            } catch (err) {
                setError('네트워크 오류가 발생했습니다.');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, [token, isAuthenticated, isAuthLoading]);

    const handleUpdateProfile = async (field: string, value: string) => {
        setMessage('');
        setError('');
        setLoading(true);

        try {
            const updateData: { [key: string]: string } = {};
            updateData[field] = value;

            const response = await fetch('http://localhost:80/api/user/me', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(updateData),
            });

            if (response.ok) {
                setMessage(`${field === 'nickname' ? '닉네임' : '이름'}이 성공적으로 업데이트되었습니다.`);
                setUserData((prev: any) => ({ ...prev, [field]: value }));
                if (field === 'nickname') setIsEditingNickname(false);
                if (field === 'name') setIsEditingName(false);
            } else {
                const errorText = await response.text();
                setError(`정보 업데이트에 실패했습니다: ${errorText}`);
            }
        } catch (err) {
            setError('네트워크 오류가 발생했습니다.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const handleChangePassword = async () => {
        setMessage('');
        setError('');

        const newPass = prompt("새 비밀번호를 입력하세요:");
        if (!newPass) {
            setMessage("비밀번호 변경이 취소되었습니다.");
            return;
        }

        const confirmPass = prompt("새 비밀번호를 다시 입력하세요:");
        if (newPass !== confirmPass) {
            setError("비밀번호가 일치하지 않습니다.");
            return;
        }

        setLoading(true);
        try {
            const response = await fetch('http://localhost:80/api/user/me', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ password: newPass }),
            });

            if (response.ok) {
                setMessage("비밀번호가 성공적으로 변경되었습니다.");
            } else {
                const errorText = await response.text();
                setError(`비밀번호 변경에 실패했습니다: ${errorText}`);
            }
        } catch (err) {
            setError('네트워크 오류가 발생했습니다.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <div className={styles.container}>
                <div className={styles.content}>
                    <p>로딩 중...</p>
                </div>
            </div>
        );
    }

    if (error && error !== '로그인이 필요합니다.') {
        return (
            <div className={styles.container}>
                <div className={styles.content}>
                    <p>오류: {error}</p>
                </div>
            </div>
        );
    }

    if (!isAuthenticated) {
        return (
            <div className={styles.container}>
                <div className={styles.content}>
                    <p>{error}</p>
                </div>
            </div>
        );
    }

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                {/* 내 정보 */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>내 정보</h2>
                    <div className={styles.infoGrid}>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>이메일</span>
                            <span className={styles.infoValue}>{userData?.email || 'N/A'}</span>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>닉네임</span>
                            {isEditingNickname ? (
                                <input
                                    type="text"
                                    value={newNickname}
                                    onChange={(e) => setNewNickname(e.target.value)}
                                    style={{ flexGrow: 1, padding: '4px', border: '1px solid #ccc', borderRadius: '4px' }} // 기본 스타일
                                />
                            ) : (
                                <span className={styles.infoValue}>{userData?.nickname || 'N/A'}</span>
                            )}
                            {isEditingNickname ? (
                                <>
                                    <button
                                        onClick={() => handleUpdateProfile('nickname', newNickname)}
                                        className={styles.changeButton} style={{ marginLeft: '8px', backgroundColor: '#28a745', color: 'white', border: 'none' }} // 임시 스타일
                                    >
                                        저장
                                    </button>
                                    <button
                                        onClick={() => { setIsEditingNickname(false); setNewNickname(userData?.nickname || ''); }}
                                        className={styles.changeButton} style={{ marginLeft: '8px', backgroundColor: '#6c757d', color: 'white', border: 'none' }} // 임시 스타일
                                    >
                                        취소
                                    </button>
                                </>
                            ) : (
                                <button onClick={() => setIsEditingNickname(true)} className={styles.changeButton}>변경</button>
                            )}
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>이름</span>
                            {isEditingName ? (
                                <input
                                    type="text"
                                    value={newName}
                                    onChange={(e) => setNewName(e.target.value)}
                                    style={{ flexGrow: 1, padding: '4px', border: '1px solid #ccc', borderRadius: '4px' }} // 기본 스타일
                                />
                            ) : (
                                <span className={styles.infoValue}>{userData?.name || 'N/A'}</span>
                            )}
                            {isEditingName ? (
                                <>
                                    <button
                                        onClick={() => handleUpdateProfile('name', newName)}
                                        className={styles.changeButton} style={{ marginLeft: '8px', backgroundColor: '#28a745', color: 'white', border: 'none' }} // 임시 스타일
                                    >
                                        저장
                                    </button>
                                    <button
                                        onClick={() => { setIsEditingName(false); setNewName(userData?.name || ''); }}
                                        className={styles.changeButton} style={{ marginLeft: '8px', backgroundColor: '#6c757d', color: 'white', border: 'none' }} // 임시 스타일
                                    >
                                        취소
                                    </button>
                                </>
                            ) : (
                                <button onClick={() => setIsEditingName(true)} className={styles.changeButton}>변경</button>
                            )}
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>비밀번호</span>
                            <span className={styles.infoValue}>●●●●●●●●</span>
                            <button onClick={handleChangePassword} className={styles.changeButton}>변경</button>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>국가</span>
                            <span className={styles.infoValue}>{userData?.country || 'N/A'}</span>
                        </div>
                        <div className={styles.infoRow}>
                            <span className={styles.infoLabel}>SMS 인증</span>
                            <span className={styles.infoValue}>{userData?.isSmsVerified ? '인증됨' : '인증 정보가 없습니다.'}</span>
                        </div>
                    </div>
                </section>

                {message && <p style={{ color: 'green', textAlign: 'center', marginTop: '20px' }}>{message}</p>}
                {error && <p style={{ color: 'red', textAlign: 'center', marginTop: '20px' }}>{error}</p>}

                {/* 연결된 SNS 계정 (기존 코드 유지) */}
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

                {/* 연결된 서비스 (기존 코드 유지) */}
                <section className={styles.section}>
                    <h2 className={styles.sectionTitle}>연결된 서비스</h2>
                    <div className={styles.serviceItem}>
                        <img src="https://weverse.io/favicon.ico" alt="Weverse logo" />
                        <div>
                            <p className={styles.serviceName}>Weverse</p>
                            <p className={styles.serviceDate}>가입일 {userData?.createdAt ? new Date(userData.createdAt).toLocaleDateString() : 'N/A'}</p>
                        </div>
                        <button className={styles.serviceButton}>서비스 탈퇴</button>
                    </div>
                </section>

                {/* 이벤트/혜택 알림 설정 (기존 코드 유지) */}
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

                {/* 맞춤형 광고 및 서비스 보기 (기존 코드 유지) */}
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

                {/* 약관 및 정책 (기존 코드 유지) */}
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