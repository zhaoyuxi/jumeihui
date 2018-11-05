package com.xixi.tester;

import com.xixi.comm.basic.TemplateType;
import com.xixi.comm.home.ArticleWorks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

final public class ArticleWorksGen {
    private static String[] authorsName = {"刀一郎", "追梦的老男人", "孤星"};
    private static String[] titles = {"让民营企业尽享政策红利（支持民营企业在行动)",
            "特朗普面临\"中期选举\"大考 这个关键点定输赢",
            "谭雅vs刀锋女皇？一段被封印在星际和红警里的花滑秘史",
            "刘亦菲幼儿园照曝光 仙女姐姐真的从小美到大",
            "苹果新款手机被曝全面砍单 富士康或被迫裁员",
            "Mate 20 Pro下边缘轻微绿屏 华为：不影响使用"};
    private static String[] images = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441676215&di=5ab6868fd434d4fa800a014496c9fdf1&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F25%2F99%2F58%2F58aa038a167e4_1024.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707704&di=23d8432036f8b340e68e9c66c54086de&imgtype=0&src=http%3A%2F%2Fpic11.photophoto.cn%2F20090603%2F0034034495016977_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707703&di=299fa7957fab5143558e05996472e7a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D936752218d94a4c21e2eef68669d71a0%2F7c1ed21b0ef41bd5e8815bda5bda81cb38db3dc9.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707703&di=0c485315aefe424b0750360a87a8ae08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D9213249f8a0a19d8df0e8c465b93e8fe%2Fbd3eb13533fa828bf8b2e0cdf71f4134970a5a0e.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707701&di=59709e37ccf78b0d19cce63c797b0c13&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D1e73931d22381f308a1485eac168267d%2Fe824b899a9014c08ee5a8980007b02087bf4f4b6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707697&di=86b8fe423af0d0a9cb4c81e6a0b3c6e2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F21a4462309f79052df1c9eea06f3d7ca7acbd5e7.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441707696&di=aaaf310b17c1c821e7523f2a235eb559&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F730e0cf3d7ca7bcb85b85a16b5096b63f724a8cc.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842870&di=ec9e7bd7e67ffb67e08980ea527c8179&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0bd162d9f2d3572c25e340088013632763d0c3e5.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842869&di=8c05f73d73eb8c39db4368def8113d23&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D890c50c9db00baa1ae214ff82f79d367%2Fcc11728b4710b912decd6bdbc9fdfc0392452282.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842869&di=a4d1b6da167d6fc93d18eb2f103f7d94&imgtype=0&src=http%3A%2F%2Fphoto.16pic.com%2F00%2F53%2F72%2F16pic_5372096_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842869&di=f7ef1a3a3b65c1c0cb6906a79e203af0&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D4ecae4251530e924dba9947224610473%2Fb999a9014c086e065457c66608087bf40ad1cb24.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842868&di=3bc519bd50403eaa2495827b17cd9f5d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F50da81cb39dbb6fd19e956f20324ab18962b37b7.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842861&di=c6a5ebf5457c57b325d930b34eb59025&imgtype=0&src=http%3A%2F%2Fpic17.photophoto.cn%2F20101015%2F0034034852991556_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842857&di=14f56fff99e1af31a67f7bc5ce63a54b&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842856&di=4f456687f97be99844d12ab34a84bbd8&imgtype=0&src=http%3A%2F%2Fimg2.3lian.com%2F2014%2Ff4%2F139%2Fd%2F119.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441842855&di=9b911dd4fcb394d5d59eae88c5d31be3&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F84%2F22%2F37u58PICzZ6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441918868&di=b3acf10231714c978177d2441b2c2e26&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fadaf2edda3cc7cd90819eb0d3301213fb80e912a.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541441981220&di=db31e4d600c0a9dc6d1082c9a322c624&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F02%2F41%2F46q58PICNMG.jpg"};

    public static List<ArticleWorks> Generate(int number) {
        List<ArticleWorks> worksList = new ArrayList<ArticleWorks>(number);
        Random ra = new Random();
        for (int i = 0; i < number; i++) {
            ArticleWorks works = new ArticleWorks();
            works.setTitle(titles[ra.nextInt(authorsName.length)]);
            works.setAuthorName(authorsName[ra.nextInt(authorsName.length)]);
            works.setAuthorId(String.valueOf(ra.nextInt()));
            works.setPublishTimeStamp(System.currentTimeMillis() - ra.nextInt(1000 * 3600 * 24 * 10));
            works.setCommentsCounter(ra.nextInt(1000 * 10000));

            int templateTypeIndex = ra.nextInt(TemplateType.size());
            TemplateType templateType = TemplateType.toTemplateType(templateTypeIndex);
            works.setTemplateType(templateType);
            switch (templateType) {
                case FullWords: {
                    break;
                }
                case WordsLeftImageRight: {
                    works.setImage1URL(images[ra.nextInt(images.length)]);
                    break;
                }
                case WordsTop1ImageBelow: {
                    works.setImage1URL(images[ra.nextInt(images.length)]);
                    break;
                }
                case WordsTop3ImageBelow: {
                    works.setImage1URL(images[ra.nextInt(images.length)]);
                    works.setImage2URL(images[ra.nextInt(images.length)]);
                    works.setImage3URL(images[ra.nextInt(images.length)]);
                    break;
                }
                case Unknown:
                default:
                    // TODO Logo
                    return null;
            }

            worksList.add(works);
        }

        return worksList;
    }
}