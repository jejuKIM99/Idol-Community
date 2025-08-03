// data/mockData.ts (Updated)

export interface Banner {
  imgSrc: string;
  albumArt?: string;
  title: string;
  subtitle: string;
  artistName?: string; 
}

export interface Product {
  id: number;
  name: string;
  price: number;
  imageUrl: string;
  category?: string;
  artistName?: string; // 상품에 아티스트 이름 추가
  detailImageUrl?: string; // 상세 정보 이미지 URL
}

export interface Artist {
  id: number;
  name: string;
  logoUrl: string;
  products: Product[];
  banners?: Banner[];
}

export interface Notice {
    id: number;
    category: string;
    title: string;
}

export const mainBanners: Banner[] = [
  {
    imgSrc: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/banner/GL-Main-Banner-240718-V-Solo-2nd-Album-MUSE-1.jpg',
    albumArt: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/p/20240718-V_MUSE_WVS-GL_1Q_FS/a804791e8470a4860b093240212f451f.jpg',
    title: "V 'MUSE'",
    subtitle: "지금 위버스샵에서 예약 판매 중!",
    artistName: 'BTS',
  },
  {
    imgSrc: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/banner/GL-Main-Banner-240715-CLOSE-YOUR-EYES-SNOWY-SUMMER-OFFICIAL-MERCH-1.jpg',
    albumArt: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/p/20240715-CLOSE-YOUR-EYES-SNOWY-SUMMER_ALBUM_1Q_FS/417d47864f7624190840b25d19d8544d.png',
    title: "CLOSE YOUR EYES SNOWY SUMMER",
    subtitle: "OFFICIAL MERCH",
    artistName: 'TOMORROW X TOGETHER',
  },
];

const detailImagePlaceholder = 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/d/20240419-BTS-POP-UP_Permission-to-Dance-in-SEOUL_S-S-T-SHIRT_BK_1Q_FD/18d87635c03c5453e938992147395048.jpg';

const sampleProducts: Product[] = [
    { id: 1, name: '[MUSE] Charm of HOPE (Set)', price: 98700, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240315-j-hope_SET_1Q_FS/e50058b88301e40348a07722100868a8.jpg', category: 'ALBUM', detailImageUrl: detailImagePlaceholder },
    { id: 2, name: '[MUSE] Charm of HOPE (MONO VER.)', price: 32900, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240315-j-hope_MONO_1Q_FS/01e51381e05a543f9a746091219b457e.jpg', category: 'ALBUM', detailImageUrl: detailImagePlaceholder },
    { id: 3, name: '[MUSE] Charm of HOPE (COLOR VER.)', price: 32900, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240315-j-hope_COLOR_1Q_FS/84683f0f3532f41648a1492576b539c3.jpg', category: 'ALBUM', detailImageUrl: detailImagePlaceholder },
    { id: 4, name: '[MUSE] Charm of HOPE (Sweet Dreams ver.)', price: 32900, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240315-j-hope_SWEET-DREAMS_1Q_FS/a8057e00889984920808a3d3c8c6f212.jpg', category: 'ALBUM', detailImageUrl: detailImagePlaceholder },
    { id: 5, name: 'S/S T-shirt (BLACK)', price: 59000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240419-BTS-POP-UP_Permission-to-Dance-in-SEOUL_S-S-T-SHIRT_BK_1Q_FS/1f6187515f7564d7fa39a2d0c2e361ac.jpg', category: 'TOUR MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 6, name: 'ARMY MEMBERSHIP', price: 25000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/membership/s/ARMY-MEMBERSHIP_1Q_FS/38171128c682745a999710202e21245e.png', category: 'MEMBERSHIP', detailImageUrl: detailImagePlaceholder },
    { id: 7, name: '2025 BTS FESTA : CAPSULE ALBUM Vol.1', price: 17800, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240604-2024-BTS-FESTA_CAPSULE-ALBUM_1Q_FS/ac139046f564f4340b08082692226f3b.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 8, name: 'Tote Bag', price: 73000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240419-BTS-POP-UP_Permission-to-Dance-in-SEOUL_TOTE-BAG_1Q_FS/e521098516d7a46879815041a99f1874.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 101, name: '[TXT X niceghostclub] Windbreaker', price: 109000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240426-TXT_niceghostclub_WINDBREAKER_1Q_FS/d1519d5c4149e49188a18357a7990141.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 102, name: '[TXT X SENTIMENTS] Ring', price: 55000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240426-TXT_SENTIMENTS_RING_1Q_FS/67448208442a44b1c855a00a221f7360.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 103, name: '[TXT X SENTIMENTS] Earring', price: 55000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240426-TXT_SENTIMENTS_EARRING_1Q_FS/e403d1964263f45c9944d18e8748382d.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 104, name: 'Belt', price: 36000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240426-TXT_WORLD-TOUR_BELT_1Q_FS/25367675e46644f8f830635e98544e3f.jpg', category: 'TOUR MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 105, name: 'Official Light Stick Ver.2', price: 49000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240219-TXT_LIGHT-STICK-VER-2_1Q_FS/c901e637149d54e56b464019277f070b.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder },
    { id: 201, name: 'SEVENTEEN TOUR \'FOLLOW\' AGAIN TO SEOUL T-SHIRT', price: 45000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20240412-SVT_TOUR-FOLLOW-AGAIN-TO-SEOUL_S-S-T-SHIRT_WH_1Q_FS/4462153c3e8034d6185854612348981f.jpg', category: 'TOUR MERCH', detailImageUrl: detailImagePlaceholder},
    { id: 202, name: 'SEVENTEEN OFFICIAL LIGHT STICK VER.3', price: 62000, imageUrl: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/merch/s/20230818-SVT_LIGHT-STICK-VER-3_1Q_FS/0161476d05f8841459a0f0270a417641.jpg', category: 'MERCH', detailImageUrl: detailImagePlaceholder},
];

const assignProducts = () => sampleProducts.sort(() => 0.5 - Math.random()).slice(0, Math.floor(Math.random() * 6) + 6);

export const allArtists: Artist[] = [
  { 
    id: 1, 
    name: 'BTS', 
    logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/3/logo/001_logo_1676529241584.png', 
    products: assignProducts(),
    banners: [
        { imgSrc: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/banner/GL-Artist-Banner-240419-BTS-POP-UP-PERMISSION-TO-DANCE-in-SEOUL-1.jpg', title: 'BTS POP-UP', subtitle: 'Permission to Dance in SEOUL' },
        { imgSrc: 'https://weverseshop.io/weverseshop-cdn/media/kr/shop/banner/GL-Artist-Banner-240718-V-Solo-2nd-Album-MUSE-1.jpg', title: '[SPECIAL GIFT] Charm of HOPE', subtitle: '지금 바로 만나보세요' }
    ]
  },
  { id: 2, name: 'TOMORROW X TOGETHER', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/4/logo/001_logo_1676529252045.png', products: assignProducts() },
  { id: 4, name: 'SEVENTEEN', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/5/logo/001_logo_1676529261390.png', products: assignProducts() },
  { id: 5, name: 'fromis_9', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/14/logo/001_logo_1676529348982.png', products: assignProducts() },
  { id: 9, name: 'ENHYPEN', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/8/logo/001_logo_1676529294248.png', products: assignProducts() },
  { id: 10, name: 'LE SSERAFIM', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/43/logo/001_logo_1676529643485.png', products: assignProducts() },
  { id: 11, name: 'NewJeans', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/52/logo/001_logo_1676529732194.png', products: assignProducts() },
  { id: 8, name: 'TWS', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/70/logo/001_logo_1705541609139.png', products: assignProducts() },
  { id: 3, name: 'GFRIEND', logoUrl: 'https://weverseshop.io/weverseshop-cdn/media/jp/shop/artist/12/logo/001_logo_1676529330666.png', products: assignProducts() },
];

// 모든 상품 목록을 생성 (artistName 포함) 및 export
export const allProducts: Product[] = allArtists.flatMap(artist => 
    artist.products.map(product => ({
        ...product,
        artistName: artist.name,
    }))
);

export const bestProducts: Product[] = [...allProducts].sort(() => 0.5 - Math.random()).slice(0, 8);

export const notices: Notice[] = [
    { id: 1, category: 'Service', title: 'Weverse Shop 개인정보처리방침 개정 안내 (2024.08.08)'},
    { id: 2, category: 'Service', title: 'Weverse Shop 이용약관 개정 안내 (2024.08.08)'},
    { id: 3, category: 'Artist', title: 'V Solo Album ‘MUSE’ 예약 판매 안내'},
    { id: 4, category: 'Artist', title: 'TOMORROW X TOGETHER minisode 3: TOMORROW with Apple Music...'},
];
