import io
import contextlib
import traceback


def run_code(code):
    output = io.StringIO()

    try:
        with contextlib.redirect_stdout(output):
            exec(
                compile(code, "<pymaster>", "exec"),
                {"__name__": "__main__"}
            )

        result = output.getvalue()

        if not result:
            return "برنامه با موفقیت اجرا شد!"

        return result

    except Exception:
        return output.getvalue() + traceback.format_exc()