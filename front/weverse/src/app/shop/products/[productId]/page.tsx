// app/shop/products/[productId]/page.tsx (Modified)
"use client";

import React, { useState, useEffect } from 'react';
import { useParams, useRouter } from 'next/navigation';
import Header from '@/components/Header';
import styles from '@/styles/ProductDetailPage.module.css';
import { FiMinus, FiPlus } from 'react-icons/fi';
import BestProductSection from '@/components/shop/BestProductSection';

interface ShopProductDetailDTO {
  productId: number;
  productName: string;
  description: string;
  price: number;
  stockQty: number;
  groupId: number;
  artistId: number;
  category: {
    categoryId: number;
    categoryName: string;
    parentCategory: {
      categoryId: number;
      categoryName: string;
    } | null;
  } | null;
  images: {
    imageUrl: string;
    imageType: string;
    sortOrder: number;
  }[];
  options: {
    optionName: string;
    stockQty: number;
    additionalPrice: number;
  }[];
  notice: {
    productName: string;
    material: string;
    sizeInfo: string;
    components: string;
    manufacturer: string;
    originCountry: string;
    manufactureDate: string;
    certificationInfo: string;
    careInstructions: string;
    qualityGuarantee: string;
    sellerName: string;
    sellerLicenseNo: string;
    phone: string;
    email: string;
    address: string;
  } | null;
}

interface ProductDTO {
  productId: number;
  productName: string;
  description: string;
  price: number;
  stockQty: number;
  images: {
    imageUrl: string;
    imageType: string;
  }[];
  category: {
    id: number;
    name: string;
  };
}

interface ShopProductDTO {
  productId: number;
  productName: string;
  price: number;
  productImage: string;
  artistId: number;
  artistName: string;
}

interface ShopArtistDTO {
  artistId: number;
  groupId: number;
  stageName: string;
  email: string;
  password?: string;
  profileImage: string;
  snsUrl: string;
  birthday: string;
  statusMessage: string;
  dmNickname: string;
}

const ProductDetailPage = () => {
  const params = useParams();
  const router = useRouter();
  const productId = params.productId as string;
  
  const [product, setProduct] = useState<ShopProductDetailDTO | null>(null);
  const [relatedProducts, setRelatedProducts] = useState<ShopProductDTO[]>([]);
  const [artistName, setArtistName] = useState<string | null>(null); // 아티스트 이름 상태 추가
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [quantity, setQuantity] = useState(1);
  const [activeTab, setActiveTab] = useState('details');

  useEffect(() => {
    const fetchProductDetail = async () => {
      let foundArtist: ShopArtistDTO | undefined;
      try {
        const response = await fetch(`http://localhost:80/api/shop/products/${productId}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data: ShopProductDetailDTO = await response.json();
        setProduct(data);

        // 아티스트 이름 가져오기
        if (data.artistId) {
          const artistsResponse = await fetch('http://localhost:80/api/shop/artists');
          if (artistsResponse.ok) {
            const allArtists: ShopArtistDTO[] = await artistsResponse.json();
            foundArtist = allArtists.find(a => a.artistId === data.artistId);
            if (foundArtist) {
              setArtistName(foundArtist.stageName);
            }
          }
        }

        // 관련 상품 가져오기
        if (data.artistId) {
          const relatedResponse = await fetch(`http://localhost:80/api/shop/artists/${data.artistId}/products`);
          if (relatedResponse.ok) {
            const relatedData: ProductDTO[] = await relatedResponse.json();
            // 현재 상품 제외 및 ShopProductDTO 형식으로 변환
            const mappedRelatedProducts: ShopProductDTO[] = relatedData
              .filter(p => p.productId !== data.productId)
              .map(p => ({
                productId: p.productId,
                productName: p.productName,
                price: p.price,
                productImage: p.images[0]?.imageUrl || '', // 첫 번째 이미지를 대표 이미지로 사용
                artistId: data.artistId, // 현재 상품의 artistId 사용
                artistName: foundArtist?.stageName || '', // 아티스트 이름 추가
              }));
            setRelatedProducts(mappedRelatedProducts);
          }
        }

      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    if (productId) {
      fetchProductDetail();
    }
  }, [productId]);

  if (loading) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.loading}>로딩 중...</div>
        </main>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.error}>오류: {error}</div>
        </main>
      </>
    );
  }

  if (!product) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.notFound}>상품을 찾을 수 없습니다.</div>
        </main>
      </>
    );
  }

  const handleQuantityChange = (amount: number) => {
    setQuantity(prev => Math.max(1, prev + amount));
  };

  const totalPrice = product.price * quantity;

  const handlePurchase = () => {
    // 결제 페이지로 이동하면서 상품 정보를 쿼리 파라미터로 전달
    router.push(`/shop/checkout?productId=${product.productId}&quantity=${quantity}&totalPrice=${totalPrice}`);
  };

  // 메인 이미지 URL 가져오기 (imageType이 'main'인 이미지)
  const mainImageUrl = product.images.find(img => img.imageType === 'main')?.imageUrl || product.images[0]?.imageUrl;

  // 상세 정보 이미지 URL 가져오기 (imageType이 'detail'인 이미지)
  const detailImageUrl = product.images.find(img => img.imageType === 'detail')?.imageUrl;

  return (
    <>
      <Header />
      <main className={styles.container}>
        <div className={styles.productSummary}>
          <div className={styles.imageWrapper}>
            <img src={`http://localhost:80${mainImageUrl}`} alt={product.productName} className={styles.productImage} />
          </div>
          <div className={styles.infoWrapper}>
            <p className={styles.artistName}>{artistName || product.artistId} &gt;</p> {/* artistName이 있으면 표시, 없으면 artistId 표시 */}
            <h1 className={styles.productName}>{product.productName}</h1>
            <p className={styles.productPrice}>{product.price.toLocaleString()}원</p>
            
            <div className={styles.divider}></div>

            <div className={styles.quantitySelector}>
              <span>수량</span>
              <div className={styles.quantityControls}>
                <button onClick={() => handleQuantityChange(-1)}><FiMinus /></button>
                <span>{quantity}</span>
                <button onClick={() => handleQuantityChange(1)}><FiPlus /></button>
              </div>
            </div>
            
            <div className={styles.divider}></div>

            <div className={styles.totalPriceSection}>
              <span>총 상품 금액</span>
              <span className={styles.totalPrice}>{totalPrice.toLocaleString()}원</span>
            </div>

            <div className={styles.actionButtons}>
              <button className={styles.buyButton} onClick={handlePurchase}>구매하기</button>
            </div>
          </div>
        </div>

        <div className={styles.productDetails}>
          <div className={styles.tabHeader}>
            <button 
              className={`${styles.tabButton} ${activeTab === 'details' ? styles.active : ''}`}
              onClick={() => setActiveTab('details')}
            >
              상세정보
            </button>
            <button 
              className={`${styles.tabButton} ${activeTab === 'basicInfo' ? styles.active : ''}`}
              onClick={() => setActiveTab('basicInfo')}
            >
              기본정보
            </button>
            <button 
              className={`${styles.tabButton} ${activeTab === 'cautions' ? styles.active : ''}`}
              onClick={() => setActiveTab('cautions')}
            >
              유의사항
            </button>
          </div>
          <div className={styles.tabContent}>
            {activeTab === 'details' && (
              <div className={styles.detailsContent}>
                {detailImageUrl ? (
                  <img src={`http://localhost:80${detailImageUrl}`} alt={`${product.productName} 상세 정보`} className={styles.detailImage} />
                ) : (
                  <p>상세 정보가 없습니다.</p>
                )}
              </div>
            )}
            {activeTab === 'basicInfo' && (
              <div className={styles.cautionsContent}> {/* cautionsContent 스타일 재활용 */}
                {product.notice ? (
                  <>
                    <h4>상품명: {product.notice.productName}</h4>
                    <p>재질: {product.notice.material}</p>
                    <p>사이즈: {product.notice.sizeInfo}</p>
                    <p>구성품: {product.notice.components}</p>
                    <p>제조사: {product.notice.manufacturer}</p>
                    <p>제조국: {product.notice.originCountry}</p>
                    <p>제조일: {product.notice.manufactureDate}</p>
                    <p>인증정보: {product.notice.certificationInfo}</p>
                    <p>취급주의사항: {product.notice.careInstructions}</p>
                    <p>품질보증기준: {product.notice.qualityGuarantee}</p>
                    <p>판매자: {product.notice.sellerName} ({product.notice.sellerLicenseNo})</p>
                    <p>연락처: {product.notice.phone}</p>
                    <p>이메일: {product.notice.email}</p>
                    <p>주소: {product.notice.address}</p>
                  </>
                ) : (
                  <p>기본 정보가 없습니다.</p>
                )}
              </div>
            )}
            {activeTab === 'cautions' && (
              <div className={styles.cautionsContent}>
                <h4>교환/환불 안내</h4>
                <p>- 상품을 수령하신 날로부터 7일 이내에만 교환/환불이 가능합니다.</p>
                <p>- 고객의 부주의로 인한 상품 훼손 및 포장 개봉의 경우 교환/환불이 불가합니다.</p>
                <h4>유의사항</h4>
                <p>- 본 상품은 한정 수량으로 제작되어 조기 품절될 수 있습니다.</p>
              </div>
            )}
          </div>
        </div>
        
        <div className={styles.relatedProductsSection}>
          {relatedProducts.length > 0 ? (
            <BestProductSection products={relatedProducts} title={`${artistName || product.artistId}의 다른 상품`} />
          ) : (
            <div className={styles.emptyRelatedProducts}>
              <p>다른 상품이 없습니다.</p>
            </div>
          )}
        </div>

      </main>
    </>
  );
};

export default ProductDetailPage;
