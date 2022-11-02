package com.shopping.wx.constant;

/**
 * @author ljy
 * @date 2022-03-11 12:40
 */
public interface AuditConstant {

    /**
     * 删除状态字段的名称
     */
    String STATUS_FIELD_NAME = "status";

    /**
     * 插入时间字段的名称
     */
    String CREATE_TIME_FIELD_NAME = "createTime";

    /**
     * id 字段的名称
     */
    String ID_FIELD_NAME = "id";

    /**
     * 默认 游客身份 前缀
     */
    String VISTOR_NAME_PREFIX = "游客_";

    /**
     * 枚举一条记录的状态（逻辑删除状态），0 启用，-1 删除
     */
    enum RecordStatus {
        /**
         * 启用状态
         */
        ACTIVE(0),
        /**
         * 删除状态
         */
        DELETED(-1);

        RecordStatus(int value) {
            this.value = value;
        }

        private final int value;

        public int value() {
            return value;
        }
    }

    /**
     * 认证状态
     */
    enum IdentifyStatus {
        SUCCESS(1),
        NORMAL(0),
        FAIL(-1);

        IdentifyStatus(int value) {
            this.value = value;
        }

        private final int value;

        public int getValue() {
            return value;
        }
    }

    /**
     * 薪资比较状态
     */
    enum SalaryCompareState {
        NEOGIATION(0),
        UNDERSALARY(1),
        UPERSALARY(2),
        BETWEENSALARY(3);

        private final int value;

        SalaryCompareState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum UserCommunityLevel {
        COMMUNITY_CIVILIAN(0),
        COMMUNITY_ADMIN(1),
        COMMUNITY_RECORDER(2);
        private final int value;

        UserCommunityLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum BuildingNameType {
        NumberType(0),
        EnglishType(1);
        private final int value;

        BuildingNameType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    enum OrderType {
        Distance(0),
        CreateTime(1);
        private final int value;

        OrderType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    enum CommunityType {
        Community(0),
        School(1);
        private final int value;

        CommunityType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * 新闻 - 文章类型
     *  保持前后台同步  标准参考数据库 information_news表 article_type字段 注释
     */
    enum InformationNewsArticleType {
        Normal(0),
        AnnouncementNews(2),
        RecruitmentNews(3),
        GoodGraduateNews(4),
        PublicInstitutionNews(5),
        PublicServantNews(6),
        SelectedStudentNews( 7 ),
        EducationTrainingNews( 8 ),
        EducationBureauExamNews( 9 ),
        TeachAndPovertyReliefNews(10 ),
        EntrepreneurshipPolicyNews(11 ),

        /**
         * 蓉社通新闻
         */
        SocialDynamicalNews(21),
        SocialSecurityNews(22),
        SocialEmploymentNews(23),
        SocialPersonnelNews(24),
        SocialMonitorNews(25),
        SocialAnnouncementNews(26),
        SocialPolicyNews(27),
        SocialIncubationNews(28),
        SocialPersonnelExamNews(29);


        private final int value;

        InformationNewsArticleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     *  面试进程(-1:不合适  0：待查看 1: 已查看 2: 待面试 3面试通过)
     */
    enum InterviewFlow {
        INTERVIEW_FAIL(-1),
        UNCHECK(0),
        CHECK(1),
        WAIT_FOR_INTERVIEW(2),
        INTERVIEW_SUCCESS(3);
        private final int value;

        InterviewFlow(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    enum JobType{

        FULL_TIME(0),
        INTERNSHIP(1);

        private final int value;
        JobType(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}

