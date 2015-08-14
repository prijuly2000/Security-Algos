
package cipher;

import org.junit.Test;



import static org.junit.Assert.*;

public class DESTest {

    // Tests using sboxes_default
    public static final long[][] testCases_default = {
            //  KEY                  PlainText            CipherText
            { 0x85bc7c34eb750ec5L, 0x0ed9e287fd025012L, 0x39182726ea841e7cL },
            { 0xfb43b47f89cfef39L, 0x550206eea4690f96L, 0x64f16a5c05466602L },
            { 0x87030bf173cf7178L, 0x0836859bdfed6139L, 0xc6d4cfbf859a154dL },
            { 0x7a55b531ddb7248eL, 0x2cdbaaec017c358eL, 0x3ae6a278e3e87106L },
            { 0x4a68ca3152722a00L, 0x3481c90cbece6cddL, 0x8fc782d035fa73b8L },
            { 0x7879669ea632d16eL, 0xf1eed838a1dbf5edL, 0x98e965b04f6a59fdL },
            { 0xa467b8b3f8b9661eL, 0x405f53c33eff1c66L, 0xc8e476d4bd308120L },
            { 0x1c67d4f6f3b2bc13L, 0xf9b518eaf299272aL, 0x063a0eb7d595d26cL },
            { 0x5bf0c102ad3485deL, 0xbc862a10fbc4dd9eL, 0xa1c3bc7682dbdc7dL },
            { 0x4c6952a2f2e4ea73L, 0xb97d46f89ed3f357L, 0x9576aeddcfb15a77L },
            { 0x5a6b9c145b62f641L, 0x795b9a097b4e2cc5L, 0xfe6f43594ecc79e2L },
            { 0x7c5543fb9aef3908L, 0x3e572881e34d2a4fL, 0xe611d946850b3b73L },
            { 0x8e9120f390701567L, 0xdeeef31374784b0fL, 0x47b2f06313b1505bL },
            { 0xd992f85bb7331d85L, 0x15234f51fed1554eL, 0x6a07cbea3550eb1dL },
            { 0x4fc6b30f403e82fcL, 0x1723a6d44391b5e8L, 0x0955d487eed206a1L },
            { 0xb869a8da1bd27be0L, 0x03fdb3387d20c333L, 0x08a5c03c7d8971aaL },
            { 0x4e3606e04e2b3a2aL, 0xa401f9a06d02f2c9L, 0x9bbe113374565d4bL },
            { 0xbd97049d69e9ef99L, 0x03712a50ddebbbb2L, 0x97582e7c93be7a7eL },
            { 0xf7a20f69cb39dafcL, 0xc2620daeb5474d73L, 0x2435c1af713e8256L },
            { 0x954b77f1f8bcaaf7L, 0x2ddf7a631fba0caaL, 0xc8c41063d77b5457L },
            { 0xf3a9883b5e9b779cL, 0xb7003ec0468484b9L, 0x7de63b83ebfc59e8L },
            { 0xc010a389fba386a3L, 0x018a6835c47ff805L, 0xef282dab61680c3cL },
            { 0xce76d592eed1676bL, 0x159421348c5fe13aL, 0x2725b0e57711557eL },
            { 0x1f2df5f3f2dc3d29L, 0xaaa1ff967f320c85L, 0x20f9719cac980deaL },
            { 0x7caeadc41826dbebL, 0x200753b6c3f88b6eL, 0xe497b1579b345cdfL },
            { 0x728daa9b0f0bdb98L, 0x3745fa5150ab1cfaL, 0x97f62a212625ee01L },
            { 0x1e852dada6ca398eL, 0x338d878c86327743L, 0xbdd989fe4a1db3b7L },
            { 0x8bf9558f76039faeL, 0x1677b3efc014412dL, 0x693202fa63b01a04L },
            { 0x01b34a23dd843393L, 0xca3a13d5af144b48L, 0xe653b1abcb486e61L },
            { 0xfe3bf993f2188394L, 0x1b8139a5b2551671L, 0x2488ff6cb9c1b1e2L },
            { 0x65c9c0f5742be4a0L, 0x5a373010ea92588eL, 0xb707e28d148cb1efL },
            { 0xcb6a1df1504fe832L, 0x87ee0379d3740d6aL, 0x8897b94b93fdab93L },
            { 0x9311484ecc8eb295L, 0xa92ef2ca8908f315L, 0xa8e86693bb8d64c4L },
            { 0x362af90f5e9cc8deL, 0x3da342eaee185edaL, 0xe40a88c9e6cb6d8bL },
            { 0x2ac6bfae836bbac0L, 0xe29464bffec8a5f4L, 0x8cb2958fa02c32b0L },
            { 0x9f280d26f4ce155fL, 0xe96555706176c025L, 0x9232abde0c422a01L },
            { 0x95961290bc8b9bdcL, 0xe6382ff081f2b85fL, 0xd029482624e621d9L },
            { 0x4cab28f2dc2fc6a2L, 0xc4722df271b8d9a6L, 0x8517ccfac916bc64L },
            { 0x73a07ca9dc50a7b9L, 0x669da7d65bfd2f32L, 0x95bb227dc7ddff1cL },
            { 0xdce33bddf5526dc1L, 0x135e91159dc47949L, 0x0a414895c8d722ebL },
            { 0x46a908155b4bf70dL, 0x71f96c503fbc0d6dL, 0xcf79f73988466316L },
            { 0xfe7e2a2c9165cf7dL, 0xff7749498a605311L, 0xa2a98a6cfa6ced30L },
            { 0x069617226d1650daL, 0x97b494adf802c088L, 0xa805ae7ea4841b9fL },
            { 0xc13cb7102807bed8L, 0xc3e9f3495d44ae12L, 0xd87bfa044b79cd9fL },
            { 0x546dd50c4e24b0e8L, 0x8b5f5e45b1e146dcL, 0xe8c19cdf07455d14L },
            { 0xa64b249dd42766f7L, 0xfac3945ebf263b67L, 0x3edfb86f9bdc02c5L },
            { 0x644ce98c9d1b1c42L, 0x5ce0c96b839c2121L, 0x4df890594810f0bcL },
            { 0x234eacb9b16c28d6L, 0x4f2e37662899ad55L, 0xc731e08f26b9438cL },
            { 0x69a1f904dfaf715dL, 0xbf78414f3fbf61aaL, 0x74522702d3761edaL },
            { 0x68338dc340fc1acdL, 0x5c89f027acfff426L, 0xe1249a510b58043fL },
            { 0xd496cf850730b6f5L, 0x4081e179d300b0d9L, 0x961b37b36721dd72L },
            { 0xc63d43d050161c69L, 0xf60d27d2c3791ad7L, 0xb152d0c45ffc3697L },
            { 0xfa7f0b2f9039416aL, 0x919f1a4474b4aa67L, 0x092b4e9dc923d319L },
            { 0xe5cce3ada1e93940L, 0x028e9fe129105c6dL, 0xb7d76a6db0900b52L },
            { 0x8369bbd7aef84671L, 0x355c4be6bd439b28L, 0x62d95adcd98770eeL },
            { 0xcfe24062ae9d6549L, 0x947c5f44b722a1e6L, 0x70843cbb775b33b2L },
            { 0xe72beffcf58f2ee0L, 0x676bf22aafdcb7d9L, 0xbf7c76bcccab16faL },
            { 0xae95473cbfbbaa9aL, 0x302c0d8acbb8adf6L, 0x2c89c75937eae139L },
            { 0xadc3f5d472853f27L, 0x537603747f24ad00L, 0x544671636d883a2bL },
            { 0x278dc22dddd67b73L, 0x456964b9f217e372L, 0x3c6f6bdafa0996acL },
            { 0x972635f707238fa1L, 0x5c96a7cb1302a0f6L, 0x2e9716a6b3afa1cbL },
            { 0x5d842ff447bb7538L, 0x926019c78efda218L, 0x7105247c6aec8ed8L },
            { 0x45c46a48b1ae5f91L, 0xd474ac14df1699bcL, 0x55c8362e36fd6320L },
            { 0xf25bc0cd8ca3f86fL, 0x4b455b4e250f7373L, 0xf386a6cc20375599L },
            { 0xc14190ea306a74b4L, 0x74d1cdfcc30c8ed4L, 0x11cc2246e54469a4L },
            { 0xc7fba056a7de8883L, 0xd3545dc654b686b8L, 0xb3e59188cc3af6a4L },
            { 0x1ed3b0415ebd25b2L, 0xc92971bbfa59f93bL, 0x7a0e49b962d181b2L },
            { 0xa2cba905645a8288L, 0xa6a080cbe199d275L, 0x0397c739735cbd22L },
            { 0xc1c500b7ac196e37L, 0x787a14b88e7e6142L, 0x4dd2fe106751deddL },
            { 0x7db6aa7fce4ef810L, 0x3098103f691c3797L, 0x8432784571d5ca70L },
            { 0xb16ea17c99c47193L, 0x03008d4bb4ea7183L, 0x97cfe5251fee0289L },
            { 0x25183b915d786f07L, 0x0d37f57630d233b7L, 0xad73fe17ae671cbfL },
            { 0x5dad9e9d4a9cbbc1L, 0x1533df4dbcbb541dL, 0x7619fac343135a99L },
            { 0xa7766cb0a053de72L, 0x78e33c1e54deb9c3L, 0x8489e607f3aa7a91L },
            { 0x31821e29726c229dL, 0x4b4d5b8d29064e79L, 0xb866af36712e0f7dL },
            { 0x95030a076e179114L, 0xf72d826f907fdb36L, 0x07ab364cdccad736L },
            { 0xd2c6b1c84923dcc3L, 0x366fab7aefad40c6L, 0xe44bbeddc6ef3350L },
            { 0xf02e7688394d5a88L, 0x0ae947195566cd69L, 0x6a353775d5a40d4cL },
            { 0x54fd41dcf1c4ededL, 0xa65506ce7f871832L, 0x1ae2cb67df420e97L },
            { 0x7b245b8b46c1729aL, 0xc1d63c7bb7eb2114L, 0x4bfedf808823a77dL },
            { 0x0d4381823b9eecb1L, 0x19e509deed410c02L, 0x839bfad8ef2aa70cL },
            { 0xd76b73c42b382d01L, 0xfb36fded6b38137bL, 0xdbc5221ecf8930a4L },
            { 0xce91a32277f8efe2L, 0x89985ec6e669f72cL, 0x97639effa23fdc1bL },
            { 0x1f0706824909ed1aL, 0xcfb48d1966730bf4L, 0x0f36be18e8252520L },
            { 0x4a04d9d515163ba3L, 0x077ae4c192c44d8fL, 0xf8b9784c2de30adcL },
            { 0x14a4b0a86172bd5cL, 0xc768953870682f80L, 0xb933cf1c8eba4fe8L },
            { 0xf501e1e4b57c5b7bL, 0xc7ac56087eaf36bdL, 0xe8ffa4a955afd8daL },
            { 0x8025956a525a6792L, 0x4e55043573eba1b2L, 0x12eac9c9565c0593L },
            { 0xbe98c67cc2e1a7c9L, 0xc8fd807b8acd92bcL, 0xd7862e39ad13621bL },
            { 0x535071fb33331eabL, 0xff74a3bb6b45837aL, 0xa170e6077505d9b3L },
            { 0xf1174770935a2b41L, 0x9cbd9160ab99b3c2L, 0x597878513d12231cL },
            { 0x749c0bdb26f33675L, 0xcf8e362d450836feL, 0x41d621d1ebf62756L },
            { 0x4811aba6c9b56f6bL, 0xe380d22ed75671deL, 0xf0b14126e3f02051L },
            { 0x353f919eba98ffc2L, 0xb90cfc0c68d097d5L, 0x9b32f6d37d73bd81L },
            { 0xd728df4acbd86c73L, 0x9e14b0ec4d1495c5L, 0x53b28a229b573fe5L },
            { 0x4d7f46265b93112fL, 0x201cb52e177db4c0L, 0x109179a432a69e37L },
            { 0x396c42fab74e2876L, 0x51cd8dbf8f972513L, 0x9ac8e03f68589850L },
            { 0x17c2d07b3ccbb017L, 0xd5d8ccd73a194bcbL, 0x2c79b9b04fc3cd57L },
            { 0x379c3396db0908b9L, 0x257519b668b05078L, 0x8c592ea5a6006362L },
            { 0x3d60f1a99096afbcL, 0x1822b04651982e29L, 0xdb64c0e5fe7915e6L }
    };

    // Tests using sboxes_default
    public static final long[][] testCases_alt1 = {
            //  KEY                  PlainText            CipherText
            //{0x2b9580d9dd49fb8fL, 0x267dad6cd4ce2777L, 0x55b4bd83296a9701L},
            {0x96ab66390b49e874L, 0x0c931ae4b5ff62c8L, 0xeef9c31b0d729785L},
            {0x3d0b15bb1ca99b7eL, 0x50336f7bc358023fL, 0x47e6243bedf65650L},
            {0xd80bbb632d8beda1L, 0x9b243192fd8efed4L, 0x6698dc2eac2569f0L},
            {0x9d509be91f9ce54aL, 0xb3e1d9af2f38a251L, 0x365f484953d10155L},
            {0xba820979a751bf4fL, 0xb6a8b2624381e175L, 0xf21311b71a637e2eL},
            {0x1bb7fecf311e1fa2L, 0x72833956557fbdbfL, 0x4f6ec85343c72582L},
            {0x660de796db320290L, 0x52db01d52ac77862L, 0x23d9a9652c89be6cL},
            {0x6efb53ef04f50cccL, 0x3afd4f4c5aa4c0fbL, 0xcc1819879f9721d2L},
            {0x4f6e16d685f983b9L, 0xc99e2a1eec23bc47L, 0xb33cac6e1518111bL},
            {0x863b23d22206d63aL, 0x4ae0c34ccb727b1dL, 0x2540d7677320c00cL},
            {0x112c18236a34d49fL, 0x524811c146eef159L, 0x24c5dde574050f0cL},
            {0x340af38b543ea663L, 0xc6d3325fb5f0df23L, 0x23895e5b29070ffbL},
            {0x86a1beda397bd446L, 0x6afe12e98f23cfa3L, 0xac90d7046133c86dL},
            {0x1902c416d6d25c5fL, 0x96a1ae7ff61a0d3cL, 0x1cc3882d05d80feaL},
            {0x90c2b6680f07ba3bL, 0xa82b5ccaf6b6c856L, 0x4177a552c5249c3dL},
            {0x9c3b7f40fea8c886L, 0x77ad1ae311ec30e7L, 0xc5c0f402192ae9f1L},
            {0x3a3a59dabff6bd5cL, 0x985e20b64f3f7fa3L, 0xd40f3ee57d2fdabbL},
            {0xa54b71ae251c33e6L, 0xbd66a268837b637dL, 0x2f02eecf50642bc3L},
            {0x9942272bbd3cf771L, 0x5362d898e4c058a9L, 0x4476a18584cc8d57L},
            {0x3103ef6822855279L, 0xede99ab19d170bfeL, 0xc798513686b88690L},
            {0x466eeadd4e69bfe9L, 0x5ae30e1c01be61edL, 0x5946e659dfafa13eL},
            {0xba87fcc0f37d9257L, 0x9e412d074a5c69eaL, 0xcafc2dec9e12aac8L},
            {0x62f8fa1abe555518L, 0x61d44500e7ba28e9L, 0x0199aa6a50e295f4L},
            {0x64f261385c3f8eb8L, 0x504cae560aff4c1fL, 0x61ee97af0df6f62fL},
            {0x84c00115f7b041d3L, 0xd5e7b297d4600f53L, 0x10c03c17ad839f0aL},
            {0x4ce20cf3755cf5c7L, 0xbf9b488be24e7d80L, 0xeeaaab804b62cfe9L},
            {0xf7e38a273b3d098fL, 0x1d060015c12d50cdL, 0xb97997906324ef12L},
            {0x14581433ecb081a0L, 0xea9246fabd864fffL, 0x0dd03f1896941aeeL},
            {0x990efc4b3b5d272aL, 0x454730b8406462ddL, 0xfbbb9832ba1551a4L},
            {0x04d61e21f86b1db5L, 0x1dfca6334c507f9bL, 0x532a5130265fb130L},
            {0x793685056aabcc49L, 0xe54528e4a137aba4L, 0x8e4609160b426222L},
            {0x2259ed4b05791e85L, 0x2039a61e07736800L, 0x41126a6630f8b5ecL},
            {0x27a9f2fa855b6f54L, 0x32eae349ce12ba08L, 0x80ee446b4575ec74L},
            {0x7e22ab2b68ec1e31L, 0xd25d9748389f2d4aL, 0xb538ae17ad9b3c0cL},
            {0xa2422bd80c33c66eL, 0xb749aa8011453c04L, 0xd5a06cd9e31d1e1fL},
            {0xe3604888f5bacee5L, 0x2cf9195f3fd45ef5L, 0x09ef587837cd795cL},
            {0xd9cb34b95ef3573aL, 0x848471593791b11fL, 0xb32e23174e16c34bL},
            {0x4b6b03e2dd8981ceL, 0xde215e47aa26cf79L, 0x8db75abff75c41f4L},
            {0x5de585627c57084fL, 0x2969f28a4d8ef184L, 0xf2a3fd979a20da3fL},
            {0xd07c73649f4396ddL, 0x9c9d7a21a5af281aL, 0x1fdd552868e27ebaL},
            {0xabbddab2ed1e1902L, 0xbaabf800094ebba0L, 0x3a9a5fc3ddcda641L},
            {0x35c92c2c584bed44L, 0xb9b92f900169ce54L, 0x158601bdd29d8cc3L},
            {0x19510210235d1a68L, 0x9fad206640cb4c35L, 0xa862039a167b93d5L},
            {0xeb57212c495c4472L, 0x1dc910a7a1d2fa9cL, 0x1f5d74d0576b8f77L},
            {0xbc078790fb68ec85L, 0x8dd0960b2fdd9662L, 0x801b4c1079086560L},
            {0xa3a65b9f42094f52L, 0xd2fc2612a795aa43L, 0x6da05bad4338b9cfL},
            {0xbc2ca9a3834e4290L, 0xc0b0b3d7c2b727c2L, 0x40da64c4dc64ccebL},
            {0x2b960f58c07d0c51L, 0xcd1f6a9c6d1cfdffL, 0x49e30b7e626beafbL},
            {0x01b09b68f097e518L, 0x9b8893a88d3b7ae0L, 0x37c61f6443b1db9aL},
            {0x727b3dd67a95bbbeL, 0xb95a39296ff91a64L, 0xfbfa97c73ba9d4ffL},
            {0x3660e83150acf614L, 0xa89b7e1576652958L, 0x531f68be05d14ea7L},
            {0xdda82542a26f89f1L, 0x9b72e4bb24d167dfL, 0xabc71a77ffd8498bL},
            {0x7fb34881da270954L, 0x62f12d3244786571L, 0x505e79b59e5bf2ccL},
            {0x35f68fbb6f3d256fL, 0x47261ec7ef963525L, 0x405b821c8a20c2cbL},
            {0x96333d5e6693ad5eL, 0x9e9f65e1be9c61fbL, 0x9afb210a75b06df1L},
            {0x77741eb000d96829L, 0x89d821844d6c63beL, 0x00e592131a29c553L},
            {0x13221cf66eb94877L, 0xf99737be00212128L, 0x5e221c29527eb5b2L},
            {0x7a56d88c969cbf67L, 0xa3603902b35a2fc9L, 0x341776b3f443db29L},
            {0xc117c293a05cd9e1L, 0x588cd128e00b358dL, 0x8b02d9249ac8450bL},
            {0x9ba55262b1b25fcdL, 0x6f3eaa383db87d71L, 0xb00b39b094d00bebL},
            {0xdcd9854d741f6c07L, 0xdefeda3e5948f187L, 0xd16cc0a9b0c47760L},
            {0x0b086d63882258a8L, 0xa863d1fa92d95633L, 0x84f00db552ffc580L},
            {0x39ffa3501468ed3dL, 0x9d76b5c5555408c7L, 0xd11766b0cf9c227dL},
            {0x7aa31b8a674c461dL, 0x7b5194693b20ba0bL, 0xdd7cefabfa1c57dcL},
            {0x6140c925695efee9L, 0x2485f853875b6978L, 0xe9e0dd69a24a35b7L},
            {0x51625e36d1f6cb21L, 0x77d3f6ad64b42a24L, 0x0a8d7e4ab02ba43bL},
            {0x8eccbe5915fa34a8L, 0xa30f192b1c1d8be7L, 0xe47377f88a50e9abL},
            {0xbaa57f4e2bfad4d4L, 0x275ac512a1b8b941L, 0x3844275cc6480801L},
            {0xb5f8a83a1b767c4eL, 0x20b82a2485f0d239L, 0xbccee724a5f1db9fL},
            {0x66c85d94e545631bL, 0x8ff0b3feecfa6471L, 0x89d964c0381b383aL},
            {0x5b62469d75bf0b63L, 0xc444e5ee464140f4L, 0x9df56a377b98d604L},
            {0xed89d76930fddb3cL, 0x185523e41b067a5bL, 0x39e5f89ea86b8e1bL},
            {0x062f1a0f22931647L, 0xf005bab8a71c0294L, 0x93b8daaaebc87cd4L},
            {0x554db987234d2f43L, 0x77abdebe992496a0L, 0xebde6504d1cca4d8L},
            {0xf39f5abfe8c2c5f3L, 0xa8b2724869e7a4cfL, 0x347c3b0e7c71b1b4L},
            {0x7daba2a2480ecc8fL, 0x4b09ed19208154d4L, 0xe8c996e365a2786bL},
            {0xe27ec0d9a58b5e37L, 0x4d55efff5b4eee0aL, 0xb7fd884e5f6e23f7L},
            {0x37ed376a0bdb258cL, 0x069c874fdd228124L, 0x91e861431f7c403aL},
            {0xb16f3c26b5b7ab6aL, 0x461ad0871b0d1e8fL, 0x793b0c7bdca18d96L},
            {0x2497943465b9f888L, 0x628ff22b7968fb37L, 0xeeaf3960c46078d7L},
            {0x5d278d4b4ba12bf8L, 0x8e8317829a268da1L, 0x9286316e02a6a9b2L},
            {0x46d4d52280ad982bL, 0xc30e51ab9fd114f9L, 0xd24588d314c615f3L},
            {0xcc16ef3c6bd2d602L, 0x70abde771df0f953L, 0x6b9cb76bdf894454L},
            {0xe7bb586c77bc773eL, 0x4ca29c4dbca92e7bL, 0xf0f5e850255255c8L},
            {0xb36c0679cde7cbe9L, 0xa3e23f4b66a51059L, 0x6a88797bb51a3156L},
            {0xa27ba9d28bb4faf9L, 0x784637eabc2edaf3L, 0xd52e2ebacb102ffcL},
            {0x076313d6e36c38a4L, 0x3350b2a51d60341aL, 0xcb03febc9b674842L},
            {0x71eadad65410808bL, 0xd7c8a52b828c47b4L, 0x9d379c08c28c0118L},
            {0x4b8ba130a87e4427L, 0x5b5230bc0c67b79aL, 0x31042d1594307f0eL},
            {0x6afe86888d4ce9bcL, 0x84dcd033f700bff7L, 0xc66cc4ffb3d05981L},
            {0xd9f4430c55766923L, 0x2ef3a0e69fd48c5fL, 0x417e14ad888695b9L},
            {0xeb575f797dfc29b4L, 0xab5a6b56a0bcb77cL, 0x426d6b879c075ea4L},
            {0x2f12a84a9c1189e6L, 0xfdaa55c837de6fcdL, 0x019b7fbe32c9e959L},
            {0xddd1b54dbaa88612L, 0xf34525aaae092faaL, 0xf7fa977fbef333d3L},
            {0x149295df26bb5529L, 0xb5c0505d6cd5ce24L, 0xbe99b33bfa106d0dL},
            {0xe18015d7bfc3238aL, 0x6fecb0f1d113b6c5L, 0x4b1d695c945905eaL},
            {0x273a8ea968fc6c0aL, 0x005b999e621ce404L, 0xab806deeb4f5a3edL},
            {0xbd70d49c3084201cL, 0x0be6d43f3cf687d9L, 0xb3238d4de4c95428L},
            {0xd704d03eab64570dL, 0x12155d89917b4afaL, 0x9b4d1ca5d8a96047L}
    };

    @Test
    public void testEncryptSingle_default() throws Exception {
    	System.out.println("Running Single Encrypt:");
        DES des = new DES("sboxes_default");
        long plaintext = 0x0123456789abcdefL;
        long key = 0x6a926bfe2a29fac3L;
        long ciphertext = des.encrypt(key, plaintext);
        long expected = 0x8ab32b4218618af7L;
        assertEquals("Test Encrypt", expected, ciphertext);
    }

    @Test
    public void testEncrypt_default() throws Exception
    {
    	System.out.println("Running Default Encrypt:");
        long[][] cases = testCases_default;
        DES des = new DES("sboxes_default");
        for(int i = 0; i < cases.length; i++)
        {
            assertEquals("Test Default Case " + i, cases[i][2], des.encrypt(cases[i][0], cases[i][1]));
        }
    }

    @Test
    public void testEncrypt_alt1() throws Exception
    {
    	System.out.println("Running Alternate Encrypt:");
        long[][] cases = testCases_alt1;
        DES des = new DES("sboxes_alt1");
        for(int i = 0; i < cases.length; i++)
        {
            assertEquals("Test Alt1 Case " + i, cases[i][2], des.encrypt(cases[i][0], cases[i][1]));
        }
    }
	   @Test
	    public void testDecryptSingle_default() throws Exception {
	    	System.out.println("Running Single Decrypt:");
	        DES des = new DES("sboxes_default");
	        long plaintext = 0x0123456789abcdefL;
	        long key = 0x6a926bfe2a29fac3L;
	        long expected = 0x8ab32b4218618af7L;
	        long ciphertext = des.decrypt(key, expected);
	        
	        assertEquals("Test Encrypt", plaintext, ciphertext);
	    }
	    
	    @Test
	    public void testDecrypt_default() throws Exception
	    {
	    	System.out.println("Running Default Decrypt:");
	        long[][] cases = testCases_default;
	        DES des = new DES("sboxes_default");
	        for(int i = 0; i < cases.length; i++)
	        {
	            assertEquals("Test Default Case " + i, cases[i][1], des.decrypt(cases[i][0], cases[i][2]));
	        }
	    }
	 
    @Test
    public void testDecrypt_alt1() throws Exception
    {
    	System.out.println("Running Alternate Encrypt:");
        long[][] cases = testCases_alt1;
        DES des = new DES("sboxes_alt1");
        for(int i = 0; i < cases.length; i++)
        {
            assertEquals("Test Alt1 Case " + i, cases[i][1], des.decrypt(cases[i][0], cases[i][2]));
        }
    }
}