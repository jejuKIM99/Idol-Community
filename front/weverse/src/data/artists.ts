export interface Member {
  name: string;
  imageUrl: string;
}

export interface Artist {
  id: number;
  name: string;
  logoUrl: string;
  imageUrl: string;
  description: string;
  members?: Member[]; // Optional members array
}

export const allArtists: Artist[] = [
  { id: 1, name: 'BTS', logoUrl: 'https://via.placeholder.com/60?text=BTS', imageUrl: 'https://via.placeholder.com/200?text=BTS', description: 'BTS는 2013년 빅히트 엔터테인먼트에서 데뷔한 7인조 대한민국의 보이그룹입니다. 방탄소년단은 방탄복처럼 사회적 편견과 억압을 막아내고, 자신들의 음악과 가치를 지켜내겠다는 의미를 담고 있습니다.',
    members: [
      { name: 'RM', imageUrl: 'https://via.placeholder.com/100?text=RM' },
      { name: '진', imageUrl: 'https://via.placeholder.com/100?text=Jin' },
      { name: '슈가', imageUrl: 'https://via.placeholder.com/100?text=Suga' },
      { name: '제이홉', imageUrl: 'https://via.placeholder.com/100?text=J-Hope' },
      { name: '지민', imageUrl: 'https://via.placeholder.com/100?text=Jimin' },
      { name: '뷔', imageUrl: 'https://via.placeholder.com/100?text=V' },
      { name: '정국', imageUrl: 'https://via.placeholder.com/100?text=Jungkook' },
    ]
  },
  { id: 2, name: 'BLACKPINK', logoUrl: 'https://via.placeholder.com/60?text=BP', imageUrl: 'https://via.placeholder.com/200?text=BLACKPINK', description: 'BLACKPINK는 YG 엔터테인먼트 소속의 4인조 걸그룹으로, 2016년 데뷔했습니다. 힙합 기반의 강렬한 음악과 퍼포먼스로 전 세계적인 인기를 얻고 있습니다.',
    members: [
      { name: '지수', imageUrl: 'https://via.placeholder.com/100?text=Jisoo' },
      { name: '제니', imageUrl: 'https://via.placeholder.com/100?text=Jennie' },
      { name: '로제', imageUrl: 'https://via.placeholder.com/100?text=Rose' },
      { name: '리사', imageUrl: 'https://via.placeholder.com/100?text=Lisa' },
    ]
  },
  { id: 3, name: 'TWICE', logoUrl: 'https://via.placeholder.com/60?text=TWICE', imageUrl: 'https://via.placeholder.com/200?text=TWICE', description: 'TWICE는 JYP 엔터테인먼트 소속의 9인조 걸그룹으로, 2015년 데뷔했습니다. 밝고 건강한 에너지와 중독성 있는 음악으로 대중의 사랑을 받고 있습니다.',
    members: [
      { name: '나연', imageUrl: 'https://via.placeholder.com/100?text=Hebi' },
      { name: '정연', imageUrl: 'https://via.placeholder.com/100?text=Jeongyeon' },
      { name: '모모', imageUrl: 'https://via.placeholder.com/100?text=Momo' },
      { name: '사나', imageUrl: 'https://via.placeholder.com/100?text=Sana' },
      { name: '지효', imageUrl: 'https://via.placeholder.com/100?text=Jihyo' },
      { name: '미나', imageUrl: 'https://via.placeholder.com/100?text=Mina' },
      { name: '다현', imageUrl: 'https://via.placeholder.com/100?text=Dahyun' },
      { name: '채영', imageUrl: 'https://via.placeholder.com/100?text=Chaeyoung' },
      { name: '쯔위', imageUrl: 'https://via.placeholder.com/100?text=Tzuyu' },
    ]
  },
  { id: 4, name: 'Hebi', logoUrl: 'https://via.placeholder.com/60?text=SKZ', imageUrl: 'https://via.placeholder.com/200?text=Stray+Kids', description: 'Hebi는 3Y코퍼레이션 소속의 솔로로, 2024년 데뷔했습니다. 직접 작사, 작곡에 참여하며 독창적인 음악 색깔을 구축하고 있습니다.', members: [{ name: 'Hebi', imageUrl: 'https://via.placeholder.com/100?text=Hebi' }] },
  { id: 5, name: 'NCT 127', logoUrl: 'https://via.placeholder.com/60?text=NCT', imageUrl: 'https://via.placeholder.com/200?text=NCT+127', description: 'NCT 127은 SM 엔터테인먼트 소속의 보이그룹 NCT의 서울 기반 유닛입니다. 다양한 음악적 시도와 퍼포먼스로 글로벌 팬덤을 확장하고 있습니다.' },
  { id: 6, name: 'Red Velvet', logoUrl: 'https://via.placeholder.com/60?text=RV', imageUrl: 'https://via.placeholder.com/200?text=Red+Velvet', description: 'Red Velvet은 SM 엔터테인먼트 소속의 5인조 걸그룹으로, 2014년 데뷔했습니다. "레드"와 "벨벳"이라는 두 가지 콘셉트를 오가며 다채로운 매력을 선보이고 있습니다.' },
  { id: 7, name: 'SEVENTEEN', logoUrl: 'https://via.placeholder.com/60?text=SVT', imageUrl: 'https://via.placeholder.com/200?text=SEVENTEEN', description: 'SEVENTEEN은 플레디스 엔터테인먼트 소속의 13인조 보이그룹으로, 2015년 데뷔했습니다. 자체 제작 아이돌로 불리며, 멤버들이 직접 작사, 작곡, 안무 제작에 참여합니다.' },
  { id: 8, name: 'ITZY', logoUrl: 'https://via.placeholder.com/60?text=ITZY', imageUrl: 'https://via.placeholder.com/200?text=ITZY', description: 'ITZY는 JYP 엔터테인먼트 소속의 5인조 걸그룹으로, 2019년 데뷔했습니다. "틴크러쉬" 콘셉트로 당당하고 자신감 넘치는 메시지를 전달하며 사랑받고 있습니다.' },
  { id: 9, name: 'aespa', logoUrl: 'https://via.placeholder.com/60?text=aespa', imageUrl: 'https://via.placeholder.com/200?text=aespa', description: 'aespa는 SM 엔터테인먼트 소속의 4인조 걸그룹으로, 2020년 데뷔했습니다. "자신의 또 다른 자아인 아바타를 만나 새로운 세계를 경험한다"는 독특한 세계관을 가지고 있습니다.' },
  { id: 10, name: 'ENHYPEN', logoUrl: 'https://via.placeholder.com/60?text=EN', imageUrl: 'https://via.placeholder.com/200?text=ENHYPEN', description: 'ENHYPEN은 빌리프랩 소속의 7인조 보이그룹으로, 2020년 데뷔했습니다. "I-LAND"를 통해 결성되었으며, 다크하면서도 몽환적인 콘셉트로 팬들을 사로잡고 있습니다.' },
  { id: 11, name: 'TXT', logoUrl: 'https://via.placeholder.com/60?text=TXT', imageUrl: 'https://via.placeholder.com/200?text=TXT', description: 'TXT (투모로우바이투게더)는 빅히트 뮤직 소속의 5인조 보이그룹으로, 2019년 데뷔했습니다. "서로 다른 너와 내가 하나의 꿈으로 모여 함께 내일을 만들어간다"는 의미를 담고 있습니다.' },
  { id: 12, name: 'ATEEZ', logoUrl: 'https://via.placeholder.com/60?text=ATZ', imageUrl: 'https://via.placeholder.com/200?text=ATEEZ', description: 'ATEEZ는 KQ 엔터테인먼트 소속의 8인조 보이그룹으로, 2018년 데뷔했습니다. "해적" 콘셉트로 강렬한 퍼포먼스와 음악을 선보이며 글로벌 팬덤을 구축하고 있습니다.' },
  { id: 13, name: '(G)I-DLE', logoUrl: 'https://via.placeholder.com/60?text=GIDLE', imageUrl: 'https://via.placeholder.com/200?text=(G)I-DLE', description: '(G)I-DLE은 큐브 엔터테인먼트 소속의 5인조 걸그룹으로, 2018년 데뷔했습니다. 멤버들이 직접 곡 작업에 참여하며 독특한 음악적 색깔을 보여주고 있습니다.' },
  { id: 14, name: 'LE SSERAFIM', logoUrl: 'https://via.placeholder.com/60?text=LSF', imageUrl: 'https://via.placeholder.com/200?text=LE+SSERAFIM', description: 'LE SSERAFIM은 쏘스뮤직 소속의 5인조 걸그룹으로, 2022년 데뷔했습니다. "IM FEARLESS"를 애너그램한 이름처럼 두려움 없이 나아가겠다는 메시지를 담고 있습니다.' },
  { id: 15, name: 'NewJeans', logoUrl: 'https://via.placeholder.com/60?text=NJ', imageUrl: 'https://via.placeholder.com/200?text=NewJeans', description: 'NewJeans는 어도어 소속의 5인조 걸그룹으로, 2022년 데뷔했습니다. 자연스럽고 편안한 매력과 트렌디한 음악으로 큰 인기를 얻고 있습니다.' },
  { id: 16, name: 'IVE', logoUrl: 'https://via.placeholder.com/60?text=IVE', imageUrl: 'https://via.placeholder.com/200?text=IVE', description: 'IVE는 스타쉽 엔터테인먼트 소속의 6인조 걸그룹으로, 2021년 데뷔했습니다. "I HAVE = IVE"라는 의미처럼 자신감 넘치는 매력을 선보이고 있습니다.' },
  { id: 17, name: 'TREASURE', logoUrl: 'https://via.placeholder.com/60?text=TR', imageUrl: 'https://via.placeholder.com/200?text=TREASURE', description: 'TREASURE는 YG 엔터테인먼트 소속의 10인조 보이그룹으로, 2020년 데뷔했습니다. "YG 보석함"을 통해 결성되었으며, 다양한 음악적 스펙트럼을 보여주고 있습니다.' },
  { id: 18, name: 'NMIXX', logoUrl: 'https://via.placeholder.com/60?text=NMX', imageUrl: 'https://via.placeholder.com/200?text=NMIXX', description: 'NMIXX는 JYP 엔터테인먼트 소속의 6인조 걸그룹으로, 2022년 데뷔했습니다. "MIXX POP"이라는 장르를 통해 다채로운 음악을 선보이고 있습니다.' },
  { id: 19, name: 'ZEROBASEONE', logoUrl: 'https://via.placeholder.com/60?text=ZB1', imageUrl: 'https://via.placeholder.com/200?text=ZEROBASEONE', description: 'ZEROBASEONE은 웨이크원 소속의 9인조 보이그룹으로, 2023년 데뷔했습니다. "보이즈 플래닛"을 통해 결성되었으며, 데뷔와 동시에 큰 인기를 얻고 있습니다.' },
  { id: 20, name: 'RIIZE', logoUrl: 'https://via.placeholder.com/60?text=RIIZE', imageUrl: 'https://via.placeholder.com/200?text=RIIZE', description: 'RIIZE는 SM 엔터테인먼트 소속의 7인조 보이그룹으로, 2023년 데뷔했습니다. "리얼타임 오디세이"라는 콘셉트로 성장하는 모습을 보여주고 있습니다.' },
  { id: 21, name: 'BOYNEXTDOOR', logoUrl: 'https://via.placeholder.com/60?text=BND', imageUrl: 'https://via.placeholder.com/200?text=BOYNEXTDOOR', description: 'BOYNEXTDOOR는 KOZ 엔터테인먼트 소속의 6인조 보이그룹으로, 2023년 데뷔했습니다. "옆집 소년들"이라는 친근한 콘셉트로 대중에게 다가가고 있습니다.' },
  { id: 22, name: 'BABYMONSTER', logoUrl: 'https://via.placeholder.com/60?text=BBM', imageUrl: 'https://via.placeholder.com/200?text=BABYMONSTER', description: 'BABYMONSTER는 YG 엔터테인먼트 소속의 7인조 걸그룹으로, 2023년 데뷔했습니다. YG의 차세대 걸그룹으로 기대를 모으고 있습니다.' },
  { id: 23, name: 'PLAVE', logoUrl: 'https://via.placeholder.com/60?text=PLV', imageUrl: 'https://via.placeholder.com/200?text=PLAVE', description: 'PLAVE는 버추얼 아이돌 그룹으로, 2023년 데뷔했습니다. 독특한 콘셉트와 뛰어난 실력으로 팬덤을 형성하고 있습니다.' },
  { id: 24, name: 'TWS', logoUrl: 'https://via.placeholder.com/60?text=TWS', imageUrl: 'https://via.placeholder.com/200?text=TWS', description: 'TWS는 플레디스 엔터테인먼트 소속의 6인조 보이그룹으로, 2024년 데뷔했습니다. "24/7 WITH US"라는 슬로건처럼 항상 함께하고 싶은 매력을 선보이고 있습니다.' },
  { id: 25, name: 'ILLIT', logoUrl: 'https://via.placeholder.com/60?text=ILLIT', imageUrl: 'https://via.placeholder.com/200?text=ILLIT', description: 'ILLIT는 빌리프랩 소속의 5인조 걸그룹으로, 2024년 데뷔했습니다. "R U Next?"를 통해 결성되었으며, 몽환적이고 신비로운 콘셉트를 가지고 있습니다.' },
  { id: 26, name: 'KISS OF LIFE', logoUrl: 'https://via.placeholder.com/60?text=KOL', imageUrl: 'https://via.placeholder.com/200?text=KISS+OF+LIFE', description: 'KISS OF LIFE는 S2 엔터테인먼트 소속의 4인조 걸그룹으로, 2023년 데뷔했습니다. 멤버들의 뛰어난 실력과 개성으로 주목받고 있습니다.' },
  { id: 27, name: 'Xdinary Heroes', logoUrl: 'https://via.placeholder.com/60?text=XH', imageUrl: 'https://via.placeholder.com/200?text=Xdinary+Heroes', description: 'Xdinary Heroes는 JYP 엔터테인먼트 소속의 6인조 밴드 그룹으로, 2021년 데뷔했습니다. "Extraordinary Heroes"라는 이름처럼 비범한 음악을 선보이고 있습니다.' },
  { id: 28, name: 'DAY6', logoUrl: 'https://via.placeholder.com/60?text=DAY6', imageUrl: 'https://via.placeholder.com/200?text=DAY6', description: 'DAY6는 JYP 엔터테인먼트 소속의 4인조 밴드 그룹으로, 2015년 데뷔했습니다. 멤버들이 직접 작사, 작곡에 참여하며 진솔한 음악을 들려주고 있습니다.' },
  { id: 29, name: 'Dreamcatcher', logoUrl: 'https://via.placeholder.com/60?text=DC', imageUrl: 'https://via.placeholder.com/200?text=Dreamcatcher', description: 'Dreamcatcher는 드림캐쳐 컴퍼니 소속의 7인조 걸그룹으로, 2017년 데뷔했습니다. 록 메탈 기반의 음악과 독특한 세계관으로 팬덤을 구축하고 있습니다.' },
  { id: 30, name: 'EVERGLOW', logoUrl: 'https://via.placeholder.com/60?text=EG', imageUrl: 'https://via.placeholder.com/200?text=EVERGLOW', description: 'EVERGLOW는 위에화 엔터테인먼트 소속의 6인조 걸그룹으로, 2019년 데뷔했습니다. 강렬한 퍼포먼스와 중독성 있는 음악으로 사랑받고 있습니다.' },
  { id: 31, name: 'fromis_9', logoUrl: 'https://via.placeholder.com/60?text=F9', imageUrl: 'https://via.placeholder.com/200?text=fromis_9', description: 'fromis_9은 플레디스 엔터테인먼트 소속의 8인조 걸그룹으로, 2018년 데뷔했습니다. "아이돌학교"를 통해 결성되었으며, 청량하고 사랑스러운 매력을 선보이고 있습니다.' },
  { id: 32, name: 'Kep1er', logoUrl: 'https://via.placeholder.com/60?text=KPL', imageUrl: 'https://via.placeholder.com/200?text=Kep1er', description: 'Kep1er는 웨이크원 소속의 9인조 걸그룹으로, 2022년 데뷔했습니다. "걸스플래닛999"를 통해 결성되었으며, 다채로운 콘셉트를 소화하며 활동하고 있습니다.' },
  { id: 33, name: 'LOONA', logoUrl: 'https://via.placeholder.com/60?text=LOONA', imageUrl: 'https://via.placeholder.com/200?text=LOONA', description: 'LOONA는 블록베리크리에이티브 소속의 12인조 걸그룹으로, 2018년 데뷔했습니다. 독특한 세계관과 멤버별 솔로 활동으로 주목받았습니다.' },
  { id: 34, name: 'MAMAMOO', logoUrl: 'https://via.placeholder.com/60?text=MMM', imageUrl: 'https://via.placeholder.com/200?text=MAMAMOO', description: 'MAMAMOO는 RBW 소속의 4인조 걸그룹으로, 2014년 데뷔했습니다. 뛰어난 가창력과 퍼포먼스로 "믿듣맘무"라는 별명을 얻었습니다.' },
  { id: 35, name: 'MONSTA X', logoUrl: 'https://via.placeholder.com/60?text=MX', imageUrl: 'https://via.placeholder.com/200?text=MONSTA+X', description: 'MONSTA X는 스타쉽 엔터테인먼트 소속의 6인조 보이그룹으로, 2015년 데뷔했습니다. 강렬한 힙합 기반의 음악과 퍼포먼스로 팬들을 사로잡고 있습니다.' },
  { id: 36, name: 'OH MY GIRL', logoUrl: 'https://via.placeholder.com/60?text=OMG', imageUrl: 'https://via.placeholder.com/200?text=OH+MY+GIRL', description: 'OH MY GIRL은 WM 엔터테인먼트 소속의 6인조 걸그룹으로, 2015년 데뷔했습니다. 요정 같은 콘셉트와 다채로운 음악으로 사랑받고 있습니다.' },
  { id: 37, name: 'P1Harmony', logoUrl: 'https://via.placeholder.com/60?text=P1H', imageUrl: 'https://via.placeholder.com/200?text=P1Harmony', description: 'P1Harmony는 FNC 엔터테인먼트 소속의 6인조 보이그룹으로, 2020년 데뷔했습니다. "피원하모니"라는 이름처럼 조화로운 음악을 선보이고 있습니다.' },
  { id: 38, name: 'SF9', logoUrl: 'https://via.placeholder.com/60?text=SF9', imageUrl: 'https://via.placeholder.com/200?text=SF9', description: 'SF9은 FNC 엔터테인먼트 소속의 9인조 보이그룹으로, 2016년 데뷔했습니다. "Sensational Feeling 9"이라는 의미처럼 감각적인 음악과 퍼포먼스를 보여주고 있습니다.' },
  { id: 39, name: 'THE BOYZ', logoUrl: 'https://via.placeholder.com/60?text=TBZ', imageUrl: 'https://via.placeholder.com/200?text=THE+BOYZ', description: 'THE BOYZ는 IST 엔터테인먼트 소속의 11인조 보이그룹으로, 2017년 데뷔했습니다. "소년들"이라는 이름처럼 청량하고 다채로운 매력을 선보이고 있습니다.' },
  { id: 40, name: 'VICTON', logoUrl: 'https://via.placeholder.com/60?text=VTN', imageUrl: 'https://via.placeholder.com/200?text=VICTON', description: 'VICTON은 IST 엔터테인먼트 소속의 6인조 보이그룹으로, 2016년 데뷔했습니다. "Voice To New World"라는 의미처럼 새로운 세상을 향한 목소리를 내고 있습니다.' },
  { id: 41, name: 'WJSN', logoUrl: 'https://via.placeholder.com/60?text=WJSN', imageUrl: 'https://via.placeholder.com/200?text=WJSN', description: 'WJSN (우주소녀)는 스타쉽 엔터테인먼트 소속의 10인조 걸그룹으로, 2016년 데뷔했습니다. 신비롭고 몽환적인 콘셉트로 사랑받고 있습니다.' },
  { id: 42, name: 'ONEUS', logoUrl: 'https://via.placeholder.com/60?text=ONEUS', imageUrl: 'https://via.placeholder.com/200?text=ONEUS', description: 'ONEUS는 RBW 소속의 6인조 보이그룹으로, 2019년 데뷔했습니다. "Us" 시리즈를 통해 다양한 음악적 스토리를 풀어내고 있습니다.' },
  { id: 43, name: 'CRAVITY', logoUrl: 'https://via.placeholder.com/60?text=CRV', imageUrl: 'https://via.placeholder.com/200?text=CRAVITY', description: 'CRAVITY는 스타쉽 엔터테인먼트 소속의 9인조 보이그룹으로, 2020년 데뷔했습니다. "Creativity"와 "Gravity"를 합친 이름처럼 독창적인 매력을 선보이고 있습니다.' },
  { id: 44, name: 'WEi', logoUrl: 'https://via.placeholder.com/60?text=WEi', imageUrl: 'https://via.placeholder.com/200?text=WEi', description: 'WEi는 위엔터테인먼트 소속의 6인조 보이그룹으로, 2020년 데뷔했습니다. "We Inspire"라는 의미처럼 팬들에게 영감을 주는 음악을 하고 있습니다.' },
  { id: 45, name: 'LIGHTSUM', logoUrl: 'https://via.placeholder.com/60?text=LTS', imageUrl: 'https://via.placeholder.com/200?text=LIGHTSUM', description: 'LIGHTSUM은 큐브 엔터테인먼트 소속의 6인조 걸그룹으로, 2021년 데뷔했습니다. "밝은 빛을 모아 세상을 비추듯, 희망의 메시지를 전달한다"는 의미를 담고 있습니다.' },
  { id: 46, name: 'STAYC', logoUrl: 'https://via.placeholder.com/60?text=STC', imageUrl: 'https://via.placeholder.com/200?text=STAYC', description: 'STAYC는 하이업엔터테인먼트 소속의 6인조 걸그룹으로, 2020년 데뷔했습니다. "Star To A Young Culture"라는 의미처럼 젊은 문화를 이끄는 스타가 되겠다는 포부를 가지고 있습니다.' },
  { id: 47, name: 'PURPLE KISS', logoUrl: 'https://via.placeholder.com/60?text=PK', imageUrl: 'https://via.placeholder.com/200?text=PURPLE+KISS', description: 'PURPLE KISS는 RBW 소속의 7인조 걸그룹으로, 2021년 데뷔했습니다. "보라색처럼 다양한 음악적 색깔을 보여준다"는 의미를 담고 있습니다.' },
  { id: 48, name: 'CLASS:y', logoUrl: 'https://via.placeholder.com/60?text=CLY', imageUrl: 'https://via.placeholder.com/200?text=CLASS:y', description: 'CLASS:y는 M25 소속의 7인조 걸그룹으로, 2022년 데뷔했습니다. "방과후 설렘"을 통해 결성되었으며, 뛰어난 실력과 퍼포먼스를 보여주고 있습니다.' },
  { id: 49, name: 'TEMPEST', logoUrl: 'https://via.placeholder.com/60?text=TMP', imageUrl: 'https://via.placeholder.com/200?text=TEMPEST', description: 'TEMPEST는 위에화 엔터테인먼트 소속의 7인조 보이그룹으로, 2022년 데뷔했습니다. "폭풍"이라는 이름처럼 가요계에 신선한 바람을 일으키겠다는 포부를 가지고 있습니다.' },
  { id: 50, name: 'TNX', logoUrl: 'https://via.placeholder.com/60?text=TNX', imageUrl: 'https://via.placeholder.com/200?text=TNX', description: 'TNX는 피네이션 소속의 6인조 보이그룹으로, 2022년 데뷔했습니다. "The New Six"라는 의미처럼 새로운 여섯 명의 멤버들이 모여 시너지를 보여주고 있습니다.' },
  { id: 51, name: 'IU', logoUrl: 'https://via.placeholder.com/60?text=IU', imageUrl: 'https://via.placeholder.com/200?text=IU', description: 'IU는 대한민국의 솔로 가수이자 배우입니다. 뛰어난 가창력과 작사, 작곡 능력을 겸비하여 대중과 평단의 사랑을 동시에 받고 있습니다.',
    members: [
      { name: '아이유', imageUrl: 'https://via.placeholder.com/100?text=IU' },
    ]
  },
];
